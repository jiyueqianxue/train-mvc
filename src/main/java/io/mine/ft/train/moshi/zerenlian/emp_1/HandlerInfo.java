package io.mine.ft.train.moshi.zerenlian.emp_1;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HandlerInfo {


	private String application;

	/**
	 * handler 名称
	 */
	private String handlerName;

	/**
	 * handler 类
	 */
	private String handlerClass;
	
	/**
     * dto 全路径 类
     */
    private String handlerDtoClass;

	/**
	 * handler 描述
	 */
	private String desc;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * handler 版本
	 */
	private String version;

	/**
	 * 注册日期
	 */
	private String regDate;

	/**
	 * 格转组字段
	 */
	private String convertStr;
	
	
}
