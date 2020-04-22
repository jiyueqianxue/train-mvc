package io.mine.ft.train.moshi.zerenlian.emp_1.handle.credit;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditApplyResultDTO;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.AbstractHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.zhujie.HandlerAnnotation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@HandlerAnnotation(desc = "授信回调")
public class WopayCreditNotifyHandler extends AbstractHandler<CreditApplyResultDTO> {

   

    @Override
    public void handle(HandlerContext<CreditApplyResultDTO> hc) {
       log.info("WopayCreditNotifyHandler handle ~~");
    }

}
