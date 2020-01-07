package io.mine.ft.train.moshi.zerenlian.emp_1.api;

import io.mine.ft.train.moshi.zerenlian.emp_1.HandlerInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;

/**
 * 类Handler.java的实现描述：模板
 * 
 * @author mark 2019年3月28日 下午12:52:10
 */
public interface Handler<I> {

    /**
     * 前置处理 如内部报文校验、处理
     *
     * @param hc
     */
    void beforeHandle(HandlerContext<I> hc);

    /**
     * 具体业务处理
     *
     * @param hc
     */
    void handle(HandlerContext<I> hc);

    /**
     * 创建handler内部域 多用于buf ----》innerParam
     *
     * @return
     */
    void genInnerParam(HandlerContext<I> hc);

    /**
     * 异常处理
     *
     * @param cause
     */
    void exceptionCaught(EngineException cause);

    /**
     * 后置处理
     *
     * @param hc
     */
    void handleCompleted(HandlerContext<I> hc);

    /**
     * 获取handler信息
     * 
     * @return
     */
    HandlerInfo getHandlerInfo();

    /**
     * 填充handler信息
     * 
     * @param handlerInfo
     */
    void setHandlerInfo(HandlerInfo handlerInfo);


}
