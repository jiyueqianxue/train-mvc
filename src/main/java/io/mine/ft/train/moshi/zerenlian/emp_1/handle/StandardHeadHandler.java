package io.mine.ft.train.moshi.zerenlian.emp_1.handle;

import org.springframework.stereotype.Component;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.AbstractHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 *
 * 
 */
@Slf4j
@Component
public class StandardHeadHandler extends AbstractHandler<Object> {
   

    @Override
    public void handle(HandlerContext<Object> hc) {
       log.info("StandardHeadHandler handle~~");
        
    }
}
