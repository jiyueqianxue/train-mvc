package io.mine.ft.train.common.utils.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * Mybatis 生成工具类
 * 
 * Created by xingliang on 2016-06-16.
 */
public class MybatisGeneratorUtil {

	public static void generate(String... xmllist) throws Throwable {
		if (null == xmllist) {
			return;
		}

		for (int i = 0; i < xmllist.length; i++) {
			List<String> warnings = new ArrayList<String>();
			boolean overwrite = true;
			String path = new File("./src/test/resources/").getAbsolutePath() + "/" + xmllist[i];
			System.out.println(path);
			
			File configFile = new File(path);
			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = cp.parseConfiguration(configFile);
			
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		}
		System.out.println("生成结束");
	}

//	public static void main(String[] args) {
//		Object obj = null;
//		System.out.println("".equals(obj));
//	}
}
