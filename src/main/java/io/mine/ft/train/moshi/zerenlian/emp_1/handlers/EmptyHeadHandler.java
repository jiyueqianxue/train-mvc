package io.mine.ft.train.moshi.zerenlian.emp_1.handlers;

import org.springframework.stereotype.Component;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;

/**
 * 类EmptyHeadHandler.java的实现描述：头模板
 * 
 * @author 2019年3月28日 下午12:48:57
 */
@Component
public class EmptyHeadHandler extends AbstractHandler<Buf> {

    @Override
    public void beforeHandle(HandlerContext<Buf> hc) {
        if (hc.getInnerParam() != null) {
            hc.getEngineInfo().setBuf(hc.getInnerParam());
        }
    }

    @Override
    public void genInnerParam(HandlerContext<Buf> hc) {
        // TODO Auto-generated method stub
    }

    @Override
    public void exceptionCaught(EngineException cause) {
        // TODO Auto-generated method stub
    }

    @Override
    public void handle(HandlerContext<Buf> hc) {
        // TODO Auto-generated method stub
    }

}
