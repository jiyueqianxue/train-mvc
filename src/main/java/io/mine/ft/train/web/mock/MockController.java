package io.mine.ft.train.web.mock;

import javax.annotation.Resource;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.mine.ft.train.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
/**
 * Spring动态 注入/删除 Bean:https://blog.csdn.net/m0_37556444/article/details/84876063
 * @author mark
 *
 */
@Slf4j
@RestController
@RequestMapping(value = "/mock/")
public class MockController {

	@Resource
    private ConfigurableApplicationContext context;

    @Resource
    private MockConfig mockConfig;
    
    /**
                *  动态绑定实现类 
     *
     * @param beanFactory
     * @param beanName
     * @param beanClazz
     */
    @RequestMapping(value = "reload")
    @ResponseBody
    public JSONObject mockReload(MockInvokeReq req) {
        log.info("mock reload={}", JSON.toJSONString(req));
        // 校验环境与入参
        JSONObject failJson = validate(req);
        if (failJson != null) {
        	log.error("mock reload validate error ~~", failJson.toString());
        	return failJson;
        }
        // 执行重新绑定bean
        synchronized (mockConfig) {
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
            removeBean(beanFactory, req.getAutowireBeanName());
            addBean(beanFactory, req.getAutowireBeanName(), req.getImplClazzFullName());
            mockConfig.reset(req.getAutowireBeanName(), req.getCostTime());
        }
        JSONObject json = new JSONObject();
        json.put("code", ResultCodeEnum.SUCCESS);
        json.put("msg", "加载实现类成功! ");
        log.info("mock reload end ~~", json.toString());
        return json;
    }
    
    /**
     * 校验环境与入参
     * @param req
     * @return
     */
    private JSONObject validate(MockInvokeReq req) {
    	JSONObject json = null;
        String env = System.getProperty("DEPLOY_ENV");
        if ("pre".equals(env) || "prd".equals(env)) {
        	json = new JSONObject();
        	json.put("code", ResultCodeEnum.SYSTEM_INNER_ERROR);
            json.put("msg", "预发或生产环境不允许执行mock!");
        }
        try {
			Class.forName(req.getImplClazzFullName());
		} catch (ClassNotFoundException e) {
			json = new JSONObject();
			json.put("code", ResultCodeEnum.PARAMS_INAVAILABLE);
            json.put("msg", e.getMessage());
		}
		return json;
    }

    /**
     * 向容器中动态添加Bean
     *
     * @param beanFactory
     * @param beanName
     * @param beanClazz
     */
    private void addBean(DefaultListableBeanFactory beanFactory, String beanName, String beanClazz) {
        BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
        BeanDefinition beanDef = beanDefBuilder.getBeanDefinition();
        if (!beanFactory.containsBeanDefinition(beanName)) {
            beanFactory.registerBeanDefinition(beanName, beanDef);
        }
    }

    /**
     * 从容器中移除Bean
     *
     * @param beanFactory
     * @param beanName
     */
    private void removeBean(DefaultListableBeanFactory beanFactory, String beanName) {
        if (beanFactory.containsBeanDefinition(beanName)) {
            beanFactory.removeBeanDefinition(beanName);
        }
    }
}
