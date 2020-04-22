package io.mine.ft.train.web.mock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * mock耗时配置
 */
@Component
public class MockConfig {
	
	private final Map<String, Integer> map= new ConcurrentHashMap<String, Integer>();
	
	public Map<String, Integer> getMap() {
		return this.map;
	}
	
	public Integer getCostTime(String beanId) {
		Integer costTime = map.get(beanId);
		if (costTime == null) {
			//返回默认mock 耗时 1000
			costTime = 1000;
		}
		return costTime;
	}
	
	public void reset(String beanId, Integer costTime) {
		map.put(beanId, costTime);
	}
}