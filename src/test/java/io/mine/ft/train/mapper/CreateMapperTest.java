package io.mine.ft.train.mapper;

import io.mine.ft.train.common.utils.generator.MybatisGeneratorUtil;

/**
 * @ClassName: CreateMapperTest
 * @Description: 工具===》生成数据层代码
 * @author: mark
 * @date: 2018年8月8日 下午3:05:09
 */
public class CreateMapperTest {
    /**
     * 每次运行都会将原来类进行覆盖，注意哟，所以只添加自己的文件哈
     */
	public static void main(String[] args) throws Throwable {
		MybatisGeneratorUtil.generate(
				
				"product_category.xml"
		);
	}
}
