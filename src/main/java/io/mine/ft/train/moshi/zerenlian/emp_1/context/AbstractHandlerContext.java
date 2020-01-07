package io.mine.ft.train.moshi.zerenlian.emp_1.context;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.mine.ft.train.moshi.zerenlian.emp_1.Branch;
import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.Handler;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandlerType;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.Instruction;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.HandlerNotFoundException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@Data
public abstract class AbstractHandlerContext<I> implements HandlerContext<I> {

    protected EngineInfo                 engineInfo;

    protected volatile HandlerContext<?> next;

    protected Handler<I>                 handler;

    protected HandleResult               handleResult = HandleResult.SUCESS;

    protected I                          innerParam;
    /**
     * 是否调用下一个handler,默认调用
     */
    protected Boolean                    isNext       = Boolean.TRUE;
    
    protected  Integer order;

    @Override
    public HandlerContext<?> getNext() {
        return next;
    }

    @Override
    public HandlerContext<?> setNext(HandlerContext<?> nextHandlerContext) {
        this.next = nextHandlerContext;
        return next;
    }

    @Override
    public Buf invokeHandler() {
        try {

            if (invokeCurrentHandler() == Instruction.BREAK) {

                if (!this.isNext) {
                    return getEngine().getLast().invokeHandler();
                } else {
                    return getEngine().getLast().invokeHandler();
                }

            }
            //执行指定handler
            if (null != this.getOrder()) {
                EnginePipeline<?, ?> engine = getEngine();
                HandlerContext[] handlerContexts = engine.getHandlerContexts();
                for (HandlerContext hc : handlerContexts) {
                    if (this.getOrder().equals(hc.getEngineInfo().getOrder())) {
                          hc.invokeCurrentHandler();
                          return hc.getNext().invokeHandler();
                    }
                }
            }

            return next.invokeHandler();
        } catch (Exception cause) {
            log.error("invokeHandler error{} ", cause);
            if (cause instanceof RemoteException || cause instanceof EngineException) {
                List<Throwable> list = ExceptionUtils.getThrowableList(cause);
                cause = list.size() < 2 ? cause : (Exception) list.get(list.size() - 1);
            }
            EngineException engineException = new EngineException(this, cause);
            /* 当前handler异常处理 */
            handler.exceptionCaught(engineException);
            /* 直接跳转到tailhandler异常处理 */
            getEngine().getLast().fireExceptionCaught(engineException);
        }
        return getBuf();
    }

    /**
     * 1.（buf--->innerParam） 生成handler内部域 2.前置处理 可用于校验、埋点、mock等相关操作 3.业务处理
     * 4.（innerParam--->buf）后置操作 填充buf、埋点、mock等相关操作
     */
    @Override
    public Instruction invokeCurrentHandler() {
        /* 1.（buf--->innerParam） 生成handler内部域 */
        handler.genInnerParam(this);
        /* 2.前置处理 可用于校验、埋点、mock等相关操作 */
        handler.beforeHandle(this);
        /* 3.业务处理 */
        handler.handle(this);

        /* 4.（innerParam--->buf）后置操作 填充buf、埋点、mock等相关操作 */
        handler.handleCompleted(this);

        /* 5.判断执行下一个 */
        if (!this.isNext) {
            return Instruction.BREAK;
        }
        
        /* 6.分支处理 当GOTO  时处理完退出 */
        if(branchInvoke(engineInfo)) {
            return Instruction.BREAK; 
        }
        
        return Instruction.CONTINUE;
    }
    
    
    
    
    /**
     * 分支调用
     *
     * @param routerInfo
     * @return boolean
     */
    public boolean branchInvoke(EngineInfo engineInfo) {
        if (CollectionUtils.isNotEmpty(engineInfo.getBranchList())) {
            /*
             * 为防止执行多次分支操作，当满足第一个分支条件后处理完成直接退出 谨慎编写分支判断条件
             */
            for (Branch branch : engineInfo.getBranchList()) {
                if (branch.excRule(getBuf().getContext())) {
                    EnginePipeline<?, ?> engine = getEngine();
                    HandlerContext<?> currentHandlerContext = engine.getHandlerContext(engineInfo.getName());
                    Buf buf = getBuf();
                    JSONObject extensionResult = JSON.parseObject(branch.excExtensionEl(buf.getContext()));
                    if (extensionResult != null)
                        buf.getContext().putAll(extensionResult);
                    switch (branch.getBranchType()) {
                  
                    /* 直接跳转到指定handler */
                    case GOTO:
                        HandlerContext<?> gotoHandlerContext = getHandlerContext(branch.getNextHandlerName());
                       
                         if (gotoHandlerContext == null) {
                            throw new HandlerNotFoundException(branch.getNextHandlerName());
                        } else {
                            currentHandlerContext.setNext(getHandlerContext(branch.getNextHandlerName()));
                            log.info(" goto handler {} " , branch.getNextHandlerName());
                            currentHandlerContext.getNext().invokeHandler();
                        }
                        return Boolean.TRUE;
                  
                    /* 终止直接退出 */
                    case TERMINATED:
                        log.info("router {} terminated", name());
                        return Boolean.TRUE;
                   
                    case GOTO_LAST:
                        log.info(" goto lastHandler {} ", getEngine().getLast().name());
                        getEngine().getLast().invokeHandler();
                        return Boolean.TRUE;
                    default:
                        break;
                    }
                    break;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public String name() {
        return engineInfo.getName();
    }

    @Override
    public String getNextHandlerName() {
        return next.name();
    }

    @Override
    public Handler<I> getHandler() {
        return handler;
    }

    @Override
    public HandlerContext<I> setHandler(Handler<I> handler) {
        this.handler = handler;
        return this;
    }

    @Override
    public HandlerContext<?> getHandlerContext(String handlerName) {
        if (handlerName.equals(name())) {
            return this;
        } else {
            return next.getHandlerContext(handlerName);
        }
    }

    @Override
    public HandlerContext<I> setEngineInfo(EngineInfo engineInfo) {
        this.engineInfo = engineInfo;
        return this;
    }

    @Override
    public HandlerContext<?> getLast() {
        return next.getLast();
    }

    @Override
    public void fireExceptionCaught(EngineException e) {
        if (e.getHandlerContext() == this) {
            handler.exceptionCaught(e);
        }
        next.fireExceptionCaught(e);
    }

    /**
     * getBuf需要子类自行实现
     *
     * @return buf
     */
    @Override
    public abstract Buf getBuf();

    @Override
    public Buf putBuf(String key, Object value) {
        return getBuf().put(key, value);
    }

    @Override
    public Buf setNewBuf(Buf buf) {
        getEngine().setBuf(buf);
        return buf;
    }

    @Override
    public boolean isRpc() {
        return engineInfo.matchHandlerType(HandlerType.RPC);
    }

    public List<EngineInfo> getNextRouterInfos(List<EngineInfo> engineInfos) {
        engineInfos.add(engineInfo);
        next.getNextRouterInfos(engineInfos);
        return engineInfos;
    }

    @Override
    public HandleResult getHandlerResult() {
        return handleResult;
    }

    @Override
    public void setHandlerResult(HandleResult handleResult) {
        this.handleResult = handleResult;
    }

    @Override
    public Boolean isNext() {
        return isNext;
    }

    @Override
    public void isNext(Boolean isNext) {
        this.isNext = isNext;

    }

    @Override
    public I getInnerParam() {
        return innerParam;
    }

    @Override
    public void setInnerParam(I innerParam) {
        this.innerParam = innerParam;
    }
    
    /**
     * 执行序号*
     */
    public void  appointOrder(Integer order) {
        this.order=order;
    };
    
    public Integer  getOrder() {
        return order;
    };
}
