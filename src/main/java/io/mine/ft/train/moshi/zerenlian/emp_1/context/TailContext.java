package io.mine.ft.train.moshi.zerenlian.emp_1.context;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.TailHandler;

/**
 * 负责生成返回报文
 *
 * @param <O>
 * @author
 */
public class TailContext<O> extends DefaultHandlerContext<Buf> implements HandlerContext<Buf> {

    private final static String  handlerName = "TAIL";


    private O                    outParam;

    public TailContext(EnginePipeline<?, O> engine, TailHandler<O> handler) {
        super(engine);
        this.handler = handler;
    }

    public O getOutParam() {
        return outParam;
    }

    public void setOutParam(O outParam) {
        this.outParam = outParam;
    }

    /**
     * tailHandler区别于其他handler 没有nexthandler调用
     */
    @Override
    public Buf invokeHandler() {
        invokeCurrentHandler();
        return getBuf();
    }

    @Override
    public String name() {
        return handlerName;
    }

    @Override
    public HandlerContext<Buf> getLast() {
        return this;
    }

    @Override
    public HandlerContext<?> getHandlerContext(String handlerName) {
        if (handlerName.equals(name()))
            return this;
        else
            return null;
    }

    @Override
    public void fireExceptionCaught(EngineException e) {
        handler.exceptionCaught(e);
    }

}
