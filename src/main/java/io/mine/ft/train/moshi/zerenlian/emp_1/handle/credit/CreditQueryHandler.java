package io.mine.ft.train.moshi.zerenlian.emp_1.handle.credit;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditQueryDTO;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.AbstractHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.zhujie.HandlerAnnotation;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询授信结果
 * 
 * @author 2019年4月4日 下午6:19:47
 */
@Slf4j
@HandlerAnnotation(desc = "查询授信处理")
public class CreditQueryHandler extends AbstractHandler<CreditQueryDTO> {

  

    @Override
    public void handle(HandlerContext<CreditQueryDTO> hc) {
        CreditQueryDTO creditQueryDTO = hc.getInnerParam();
        log.info("核心授信处理, CreditQueryHandler, handle", creditQueryDTO);
    }
}
