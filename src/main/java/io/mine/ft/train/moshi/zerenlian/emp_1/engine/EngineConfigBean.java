package io.mine.ft.train.moshi.zerenlian.emp_1.engine;
/**
 * 
 * 类EngineConfig.java的实现描述：api配置 
 * @author wanglinan 2019年4月10日 上午10:35:18
 */

import java.util.List;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Builder;

@Getter
@Setter
@Builder
public class EngineConfigBean extends ConfigBean {
    /**
     * 
     */
    private static final long serialVersionUID = 2343345498929207102L;
    
    
    public static final String ON = "on";
    public static final String off = "off";

    /**
     * 开关 on off
     */
    private String  onOff;
    /**
     * 服务名
     */
    private String            apiName;
    
    /**
     * 验证Schema
     */
    private String            validatorSchemaInfo;
    
    /**
     * 处理链路
     */
    private  List<EngineInfo> engineInfos;
    
    /**
     * api类型
     */
    private  String apiType;

}
