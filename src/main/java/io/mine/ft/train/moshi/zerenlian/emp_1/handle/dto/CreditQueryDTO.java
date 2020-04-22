package io.mine.ft.train.moshi.zerenlian.emp_1.handle.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * 类CreditQueryDTO.java的实现描述：TODO 类实现描述 
 * @author  2019年4月4日 下午6:21:40
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CreditQueryDTO {

    /**
     * 授信申请号
     */
	private String outerCreditApplyNo;
}
