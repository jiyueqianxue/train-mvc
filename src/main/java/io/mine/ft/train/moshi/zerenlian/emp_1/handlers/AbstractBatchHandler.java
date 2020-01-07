package io.mine.ft.train.moshi.zerenlian.emp_1.handlers;

import java.util.List;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.BatchHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBatchHandler<I> extends AbstractHandler<I> implements BatchHandler<I> {

    @Override
    public void handle(HandlerContext<I> hc) {
        List<I> readList;
        long length = getExecuteNum(hc);
        for (int i = 0; i < length; i++) {
            readList = read(hc, i);
            before(hc, readList);
            write(hc, readList);
            after(hc, readList);
            for (I one : readList) {
                b2oInvoke(hc, one);
            }
        }
        complete(hc);
    }

    @Override
    public List<I> read(HandlerContext<I> hc, int offset) {
        return null;
    }

    @Override
    public I convert(String s) {
        return null;
    }

    @Override
    public void before(HandlerContext<I> hc, List<I> list) {

    }

    @Override
    public void write(HandlerContext<I> hc, List<I> list) {

    }

    @Override
    public void after(HandlerContext<I> hc, List<I> list) {

    }

    @Override
    public void complete(HandlerContext<I> hc) {

    }

    public abstract long getPageSize(HandlerContext<I> hc);

    public abstract long getExecuteNum(HandlerContext<I> hc);

    public abstract Buf convert2Buf(Object o);

    @Override
    public boolean b2oInvoke(HandlerContext<I> hc, Object o) {
        Buf buf = convert2Buf(o);
        // 深拷贝新的pipe
        EnginePipeline<?, ?> routerPipeline = hc.getEngine().clone().setBuf(buf);
        return runRouterPipeline(hc, routerPipeline);
    }

    protected boolean runRouterPipeline(HandlerContext<I> hc, EnginePipeline<?, ?> enginePipeline) {
        // 有分支处理时且为GOTO ADD_PIPELINE TERMINATED 直接continue
        EngineInfo engineInfo = hc.getEngineInfo();
        log.info("runRouterPipeline engineInfo~~", engineInfo);
        return Boolean.TRUE;
    }

}
