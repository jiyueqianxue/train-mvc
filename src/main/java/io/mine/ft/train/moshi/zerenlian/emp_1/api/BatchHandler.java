package io.mine.ft.train.moshi.zerenlian.emp_1.api;

import java.util.List;

public interface BatchHandler<I> {

    /**
     * 读数据
     *
     * @param hc 上下文
     */
    List<?> read(HandlerContext<I> hc, int offset);

    /**
     * 数据转bean
     *
     * @param s 序列化字符串
     */
    I convert(String s);

    /**
     * 数据校验-前置校验
     *
     * @param hc 上下文
     */
    void before(HandlerContext<I> hc, List<I> list);

    /**
     * 写数据
     *
     * @param hc 上下文
     */
    void write(HandlerContext<I> hc, List<I> list);

    /**
     * 数据校验-后置校验
     *
     * @param hc 上下文
     */
    void after(HandlerContext<I> hc, List<I> list);

    /**
     * 最终执行完处理
     *
     * @param hc 上下文
     */
    void complete(HandlerContext<I> hc);

    /**
     * 批量转联机
     * @param hc
     * @return
     */
    boolean b2oInvoke(HandlerContext<I> hc, Object o);
}
