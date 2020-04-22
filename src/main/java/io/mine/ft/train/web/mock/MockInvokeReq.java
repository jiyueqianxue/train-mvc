package io.mine.ft.train.web.mock;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MockInvokeReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String autowireBeanName;
	private String implClazzFullName;
	private int costTime;
	
}
