package io.mine.ft.train.moshi.zerenlian.emp_1.context;

import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;

/**
 * handler 容器
 *
 * @author
 */
public class DefaultHandlerContext<I> extends AbstractHandlerContext<I> {

    private EnginePipeline<?, ?> engine;

    public DefaultHandlerContext(EnginePipeline<?, ?> engine) {
        this.engine = engine;
    }

    @Override
    public Buf getBuf() {
        return engine.getBuf();
    }

    @Override
    public EnginePipeline<?, ?> getEngine() {
        return engine;
    }

}
