package io.mine.ft.train.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import io.mine.ft.train.BaseTest;
import io.mine.ft.train.bean.base.BaseResponse;
import io.mine.ft.train.service.EngineService;

public class CreditQueryTest extends BaseTest {

	@Autowired
	private EngineService engineService;

	@Test
	public void test() {

		JSONObject json = new JSONObject();
		json.put("apiVersion", "1.0.0");
		json.put("apiName", "creditQuery");
		json.put("partnerNo", "TXWP");
		json.put("productCode", "JDZZ");
		json.put("channelNo", "TXWPA00001");
		json.put("ip", "192.172.0.0");
		json.put("client", "pc");
		json.put("reqNo", "00000999");
		json.put("thirdUserNo", "JDZZ8338635975");
		json.put("outerCreditApplyNo", "JDZZCredit0728277959012");
		json.put("reqDate", "");
		
		BaseResponse baseResponse = engineService.invoke(json.toJSONString(), json.getString("apiName"));
	    System.out.println(baseResponse);

	}

}
