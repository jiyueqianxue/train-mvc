package io.mine.ft.train.web.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import io.mine.ft.train.common.enums.ResultCodeEnum;
import io.mine.ft.train.service.ShopSupport;
import io.mine.ft.train.service.ShopSupportMock;
import io.mine.ft.train.web.mock.MockInvokeReq;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private ShopSupport shopSupport;
	
	@Autowired
	private ShopSupportMock shopSupportMock;

	@RequestMapping("/menuList")
	public ModelAndView menuList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			shopSupport.update_1();
		} catch (Exception e) {
			log.info("update商品详情：--{}", e);
		}
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	 /**
      * 测试mock绑定是否成功
     * @param req
	 * @throws Exception 
     */
    @RequestMapping(value = "/mock/invoke")
    @ResponseBody
    public JSONObject mockRepay(MockInvokeReq req) throws Exception {
    	
    	// 校验环境
    	JSONObject json = new JSONObject();
        String env = System.getProperty("DEPLOY_ENV");
        if ("pre".equals(env) || "prd".equals(env)) {
        	json.put("code", ResultCodeEnum.SYSTEM_INNER_ERROR);
            json.put("msg", "预发或生产环境不允许执行mock!");
            return json;
        }

        shopSupportMock.update_1();
        shopSupport.update_1();
//        log.info("mock invoke={}", JSON.toJSONString(baseResp));
//
//        json.put("code", RespCodeType.SUCCESS);
//        json.put("msg", "invoke成功");
//        json.put("baseResp", baseResp);
//        json.put("mockResp", mockResp);
        return json;
    }
}
