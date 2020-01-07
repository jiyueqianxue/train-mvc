package io.mine.ft.train.moshi.zerenlian.emp_1.handle;

import org.springframework.stereotype.Component;

import io.mine.ft.train.moshi.zerenlian.emp_1.context.TailContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.TailHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 类StandardTailHandler.java的实现描述：标准尾节点
 * 
 * @author 2019年3月28日 下午8:20:07
 */
@Slf4j
@Component
public class StandardTailHandler extends TailHandler<Object> {

    @Override
    public Object genOutParam(TailContext<Object> tailContext) {
        return tailContext.getBuf();
    }

    @Override
    public void exceptionCaught(EngineException cause) {
        
        log.info("StandardTailHandler handle~~");
    }
}
