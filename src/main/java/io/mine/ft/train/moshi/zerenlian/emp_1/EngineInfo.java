package io.mine.ft.train.moshi.zerenlian.emp_1;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandlerType;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EngineInfo implements Comparable<EngineInfo> {


	/**
	 * 执行序号*
	 */
	private Integer order;

	/**
	 * handlerName
	 */
	private String name;
	
	   /**
     * 分支
     */
    private List<Branch> branchList;


	/**
	 * mock标识*
	 */
	private String mock;
	/**
	 * 备注
	 */
	private String memo;
	/**
     * handler调用类型默认本地
     *
     * @see HandlerType
     */
    private String type = HandlerType.LOCAL.name();
    /**
     * 存放临时
     */
    private Buf buf;
    
    private HandlerInfo handlerInfo;
    
    public EngineInfo(String name) {
        this.name = name;
    }

    public EngineInfo(Integer order, String name) {
        this.order = order;
        this.name = name;
    }
    
    
    public EngineInfo build() {
        // 填充handlerinfo
        if (StringUtils.isBlank(name)) {
            throw new EngineException("engineInfo.name is blank");
        } 
        return this;
    }
    
    /**
     * 匹配比较handlerType
     *
     * @param handlerType
     * @return
     */
    public boolean matchHandlerType(HandlerType handlerType) {
        return HandlerType.valueOf(type) == handlerType;
    }

    @Override
    public int compareTo(EngineInfo o) {
        
        return this.order -o.getOrder();
    }
}  
