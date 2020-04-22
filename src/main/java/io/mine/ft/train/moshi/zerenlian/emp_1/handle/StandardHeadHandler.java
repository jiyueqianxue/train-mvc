package io.mine.ft.train.moshi.zerenlian.emp_1.handle;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.AbstractHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.zhujie.HandlerAnnotation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * 
 */
@Slf4j
@HandlerAnnotation(desc = "起始")
public class StandardHeadHandler extends AbstractHandler<Object> {
   

    @Override
    public void handle(HandlerContext<Object> hc) {
       log.info("StandardHeadHandler handle~~");
        
    }
}
