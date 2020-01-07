package io.mine.ft.train.moshi.zerenlian.emp_1.engine;

import java.util.ArrayList;
import java.util.List;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.HandlerInfo;

public class EngineConfigurator implements EngineConfig {

	@Override
	public List<EngineInfo> getEngineInfos(String engineName) {
		
		//走数据库获取
		List<EngineInfo> engineInfoList = new ArrayList<>();
		
		EngineInfo en = new EngineInfo();
		en.setOrder(1);
		en.setName("creditQueryHandler");
		en.setType("LOCAL");
		HandlerInfo handlerInfo = new HandlerInfo();
		handlerInfo.setHandlerName("creditQueryHandler");
		handlerInfo.setHandlerClass("creditQueryHandler");
		handlerInfo.setHandlerDtoClass("io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditQueryDTO");
		en.setHandlerInfo(handlerInfo);
		engineInfoList.add(en);
		
		EngineInfo en2 = new EngineInfo();
		en2.setOrder(2);
		en2.setName("creditResultHandler");
		en2.setType("LOCAL");
		HandlerInfo handlerInfo2 = new HandlerInfo();
		handlerInfo2.setHandlerName("creditResultHandler");
		handlerInfo2.setHandlerClass("creditResultHandler");
		handlerInfo2.setHandlerDtoClass("io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditApplyResultDTO");
		en2.setHandlerInfo(handlerInfo2);
		engineInfoList.add(en2);
		
		EngineInfo en3 = new EngineInfo();
		en3.setOrder(4);
		en3.setName("wopayCreditNotifyHandler");
		en3.setType("LOCAL");
		HandlerInfo handlerInfo3 = new HandlerInfo();
		handlerInfo3.setHandlerName("wopayCreditNotifyHandler");
		handlerInfo3.setHandlerClass("wopayCreditNotifyHandler");
		handlerInfo3.setHandlerDtoClass("io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto.CreditApplyResultDTO");
		en3.setHandlerInfo(handlerInfo2);
		engineInfoList.add(en3);
		return engineInfoList;
	}

}
