package io.mine.ft.train.moshi.zerenlian.emp_1.handlers;


import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.context.TailContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
/**
 * 
 * 类TailHandler.java的实现描述：未模板 
 * @author 2019年3月28日 下午12:49:15
 */
public abstract class TailHandler<O> extends AbstractHandler<Buf> {

	@Override
	public void beforeHandle(HandlerContext<Buf> hc) {

	}

	@Override
	public void genInnerParam(HandlerContext<Buf> hc) {

	}


	@SuppressWarnings("unchecked")
	@Override
	public void handle(HandlerContext<Buf> hc) {
		TailContext<O> tailContext = (TailContext<O>) hc;
		O outParam = genOutParam(tailContext);
		tailContext.setOutParam(outParam);
	}

	/**
	 * 生成返回报文
	 *
	 * @param tailContext
	 * @return
	 */
	public abstract O genOutParam(TailContext<O> tailContext);

	@Override
	public abstract  void exceptionCaught(EngineException cause);
	    
}
