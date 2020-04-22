package io.mine.ft.train.moshi.zerenlian.emp_1.handle.credit;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditApplyResultDTO;
import io.mine.ft.train.moshi.zerenlian.emp_1.handlers.AbstractHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.zhujie.HandlerAnnotation;
import lombok.extern.slf4j.Slf4j;

/**
 * 核心授信结果处理
 * 
 * @author Mark
 * @since 2019-04-12 18:23
 */
@Slf4j
@HandlerAnnotation(desc = "核心授信处理")
public class CreditResultHandler extends AbstractHandler<CreditApplyResultDTO> {

	@Override
	public void handle(HandlerContext<CreditApplyResultDTO> hc) {
		CreditApplyResultDTO resultDTO = hc.getInnerParam();
		log.info("核心授信处理, CreditResultHandler, handle", resultDTO);
	}
}
