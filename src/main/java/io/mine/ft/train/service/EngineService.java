package io.mine.ft.train.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import io.mine.ft.train.bean.base.BaseResponse;
import io.mine.ft.train.common.utils.SpringContextUtil;
import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.MapBuf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EngineConfig;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EngineConfigurator;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;
import io.mine.ft.train.moshi.zerenlian.emp_1.handle.StandardHeadHandler;
import io.mine.ft.train.moshi.zerenlian.emp_1.handle.StandardTailHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 类RouterService.java的实现描述：TODO 类实现描述
 * 
 * @author 2019年3月28日 下午1:37:30
 */
@Service
@Slf4j
public class EngineService {

    /**
     * 请求入口 api
     *
     * @param request 请求参数
     * @return 执行结果
     */
    @SuppressWarnings("unchecked")
//    @ApolloSentinelAnnotation(productCodeKey="productCode",partnerNoKey="partnerNo",
//    channelNoKey="channelNo",thirdUserNoKey="thirdUserNo",apiNameKey="apiName")
    public BaseResponse invoke(String request, String engineName) {
    	@SuppressWarnings("rawtypes")
		EnginePipeline enginePipeline = new EnginePipeline(
        		SpringContextUtil.getBean(StringUtils.uncapitalize(StandardHeadHandler.class.getSimpleName())),
        		SpringContextUtil.getBean(StringUtils.uncapitalize(StandardTailHandler.class.getSimpleName())),
                new EngineConfigurator());
    	enginePipeline.setEngineName(engineName);
        return doInvoke(request, enginePipeline);

    }

  

    @SuppressWarnings("unchecked")
	private BaseResponse doInvoke(String strParams, EnginePipeline pipeline) {
    	
    	String engineName = pipeline.getEngineName();
    	EngineConfig config = pipeline.getEngineConfig();
    	List<EngineInfo> engineInfos = config.getEngineInfos(engineName);
    	
        try {
    		Map<String, Object> params = JSON.parseObject(strParams);
            pipeline.init(engineInfos).fireHandlers(params);
            BaseResponse resp = null;
            if (pipeline.getResp() instanceof BaseResponse) {
                resp = (BaseResponse) pipeline.getResp();
                return resp;
            }
            MapBuf object = (MapBuf) pipeline.getResp();
            return resp;
        } catch (Exception e) {
        	BaseResponse resp = new BaseResponse();
            log.error("doInvoke error", e);
            return resp;
        } finally {
           
        }
    }
}
