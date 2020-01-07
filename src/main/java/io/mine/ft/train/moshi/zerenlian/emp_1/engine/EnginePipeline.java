package io.mine.ft.train.moshi.zerenlian.emp_1.engine;

import java.util.List;
import java.util.TreeMap;

import com.rits.cloning.Cloner;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.HandlerManager;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.Handler;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.context.AbstractHandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.context.DefaultHandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.context.TailContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.TailHandler;
import lombok.Getter;
import lombok.Setter;

/**
 * 容器
 * EnginePipeline是一个特殊的handlerContext 它的handler就是头handler 持有buf
 * 管理当前责任链所有的handlerContext 返回结果通过getResp获取
 *
 * @param <I>
 * @param <O>
 * @author 
 */
@Getter
@Setter
public class EnginePipeline<I, O> extends AbstractHandlerContext<I> implements HandlerContext<I> {

	private final static Cloner cloner = new Cloner();
	
	private TailHandler tailHandler;

	private HandlerContext[] handlerContexts;

	private TailContext<O> tail;

	private Buf buf;

	private EngineConfig engineConfig;
	
	private boolean isSinglePipe = Boolean.FALSE;

	private String engineName;


	public EnginePipeline(Handler<I> headHandler, TailHandler tailHandler, EngineConfig engineConfig) {
		this.handler = headHandler;
		this.tailHandler = tailHandler;
		this.engineConfig = engineConfig;
	}

	public EnginePipeline(Handler<I> headHandler, TailHandler tailHandler, EngineConfig engineConfig,
			String engineName) {
		this.handler = headHandler;
		this.tailHandler = tailHandler;
		this.engineConfig = engineConfig;
		this.engineName = engineName;
	}

	public List<EngineInfo> getEngineInfos(String engineName) {
		return engineConfig.getEngineInfos(engineName);
	}

	public EnginePipeline<I, O> init(List<EngineInfo> engineInfos) {
		this.setEngineInfo(new EngineInfo("HEAD"));
		fillHandlerContexts(engineInfos.size() + 2);
		setNext(handlerContexts[1]);
		int order = 1;
		for (EngineInfo engineInfo : engineInfos) {
		    engineInfo.build();
			final String name = engineInfo.getName();
			Handler handler = HandlerManager.getHandler(name);
			handlerContexts[order].setHandler(handler).setEngineInfo(engineInfo).setNext(handlerContexts[order++ + 1]);
		}
		return this;
	}

	/**
	 * 填充handlerContext
	 *
	 * @param length
	 */
	private void fillHandlerContexts(int length) {
		handlerContexts = new HandlerContext[length];
		handlerContexts[0] = this;
		for (int i = 1; i < length - 1; i++) {
			handlerContexts[i] = new DefaultHandlerContext(this);
		}
		tail = new TailContext<O>(this, tailHandler);
		tail.setEngineInfo(new EngineInfo("TAIL"));
		handlerContexts[length - 1] = tail;
	}

	/**
	 * 指定order单个调用 可用于单元测试
	 *
	 * @param order
	 * @param buf
	 */
	public void invokeHandler(int order, Buf buf) {
		this.buf = buf;
		handlerContexts[order].invokeCurrentHandler();
	}

	/**
	 * 从指定order开始调用责任链
	 *
	 * @param order
	 * @param buf
	 */
	public void invokeHandlers(int order, Buf buf) {
		this.buf = buf;
		handlerContexts[order].invokeHandler();
	}

	public Buf fireHandlers(I req) {
		setInnerParam(req);
		return invokeHandler();
	}

	public TreeMap<String, HandleResult> getHandleResults() {
		TreeMap<String, HandleResult> handleResults = new TreeMap<String, HandleResult>();
		for (HandlerContext handlerContext : handlerContexts) {
			handleResults.put(handlerContext.name(), handlerContext.getHandlerResult());
		}
		return handleResults;
	}

	public O getResp() {
		return tail.getOutParam();
	}

	/**
	 * 在指定order添加新的handlerContext
	 *
	 * @param order
	 * @param hc
	 * @return
	 */
	public EnginePipeline<I, O> addHandlerContext(int order, HandlerContext hc) {
		HandlerContext[] newHadlerContexts = new HandlerContext[handlerContexts.length + 1];
		for (int i = 0, j = 0; i < newHadlerContexts.length; i++) {
			if (i == order) {
				newHadlerContexts[i] = hc;
				continue;
			}
			newHadlerContexts[i] = handlerContexts[j];
			EngineInfo orEngineInfo = newHadlerContexts[i].getEngineInfo();
			if (orEngineInfo != null) {
			    EngineInfo newEngineInfo = cloner.deepClone(orEngineInfo);
			    newEngineInfo.setOrder(i);
				newHadlerContexts[i].setEngineInfo(newEngineInfo);
			}
			j++;
		}
		handlerContexts = newHadlerContexts;
		return this;
	}
	
	public EnginePipeline<I, O> addHandlerContexts(int order, List<HandlerContext<?>> hcs) {
		HandlerContext[] newHadlerContexts = new HandlerContext[handlerContexts.length + hcs.size()];
		for (int i = 0, j = 0; i < newHadlerContexts.length; i++) {
			if (i == order) {
				for (HandlerContext handlerContext : hcs) {
					newHadlerContexts[i] = handlerContext;
					i++;
				}
			}
			newHadlerContexts[i] = handlerContexts[j];
			EngineInfo orRouterInfo = newHadlerContexts[i].getEngineInfo();
			if (orRouterInfo != null) {
			    EngineInfo newRouterInfo = cloner.deepClone(orRouterInfo);
				newRouterInfo.setOrder(i);
				newHadlerContexts[i].setEngineInfo(newRouterInfo);
				newHadlerContexts[i].setEngineInfo(newRouterInfo);
			}
			j++;
		}
		handlerContexts = newHadlerContexts;
		return this;
	}



	public HandlerContext getHC(int order) {
		return handlerContexts[order];
	}

	public boolean isSinglePipe() {
		return isSinglePipe;
	}

	/**
	 * 克隆一个新的RouterPipe
	 */
	@Override
	public EnginePipeline<I, O> clone() {
		EnginePipeline<I, O> newPipe = new EnginePipeline<I, O>(this.handler, this.tailHandler, this.engineConfig,
				engineName);
		return newPipe.init(handlerContexts);
	}

	/**
	 * 克隆一个新的RouterPipe时初始化
	 *
	 * @param initHadlerContexts
	 * @return
	 */
	private EnginePipeline<I, O> init(HandlerContext[] initHadlerContexts) {
		this.setEngineInfo(initHadlerContexts[0].getEngineInfo());
		fillHandlerContexts(initHadlerContexts.length);
		setNext(this.handlerContexts[1]);
		for (int i = 1; i < initHadlerContexts.length - 1; i++) {
			this.handlerContexts[i].setHandler(initHadlerContexts[i].getHandler())
					.setEngineInfo(initHadlerContexts[i].getEngineInfo()).setNext(this.handlerContexts[i + 1]);
		}
		return this;
	}

	@Override
	public TailContext<O> getLast() {
		return tail;
	}

	@Override
	public Buf getBuf() {
		return buf;
	}

	public EnginePipeline<I, O> setBuf(Buf buf) {
		this.buf = buf;
		return this;
	}

	public HandlerContext<Buf> setLast(TailContext<O> tailContext) {
		this.tail = tailContext;
		tailHandler = (TailHandler) tailContext.getHandler();
		handlerContexts[handlerContexts.length - 1] = tailContext;
		return tailContext;
	}


	public TailHandler getTailHandler() {
		return tailHandler;
	}

	public Integer getIndex(String handlerName) {
		for (int i = 0; i < handlerContexts.length; i++) {
			if (handlerName.equals(handlerContexts[i].name())) {
				return i;
			}
		}
		return null;
	}

    @Override
    public EnginePipeline<?, ?> getEngine() {
        return this;
    }

}
