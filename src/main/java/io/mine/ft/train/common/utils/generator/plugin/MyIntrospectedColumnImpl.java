package io.mine.ft.train.common.utils.generator.plugin;

import org.mybatis.generator.api.IntrospectedColumn;


public class MyIntrospectedColumnImpl extends IntrospectedColumn {

	/**
	 * 不生成withblobs
	 */
	@Override
	public boolean isBLOBColumn() {
		return false;
	}
}
