package io.mine.ft.train.moshi.zerenlian.emp_1.buf;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 类MapBuf.java的实现描述：TODO 类实现描述 
 * @author  2019年3月28日 下午1:44:54
 */
@Slf4j
public class MapBuf implements Buf {


	private Map<String, Object> context;


	private String nextHandlerName;
	


	public MapBuf() {
		context = Maps.newHashMap();
	}

	public MapBuf(Map<String, Object> bufMap) {
		context = bufMap;
	}

	@Override
	public Buf put(String key, Object value) {
		context.put(key, value);
		return this;
	}

	@Override
	public Map<String, Object> getContext() {
		return context;
	}

	@Override
	public Object get(String key) {
		return context.get(key);
	}

	@Override
	public Buf remove(String key) {
		context.remove(key);
		return this;
	}

	@Override
	public void clear() {
		context.clear();
	}

	@Override
	public int size() {
		return context.size();
	}

	@Override
	public Buf object2Buf(Object o) {
		return jsonString2Buf(JSON.toJSONString(o));
	}


	@Override
	public String toString() {
		return toJsonString();
	}

	@Override
	public String toJsonString() {
		return JSON.toJSONString(this);
	}

	@Override
	public Buf jsonString2Buf(String s) {
		try{
			return JSON.parseObject(s, this.getClass());
		} catch (Exception e){
		    log.error("jsonString2Buf error " ,e);
			return null;
		}
	}

	
	

	@Override
	public String getNextHandlerName() {
		return nextHandlerName;
	}

	@Override
	public void setNextHandlerName(String nextHandlerName) {
		this.nextHandlerName = nextHandlerName;
	}


	@Override
	public String getExceptionHandlerName() {
		return null;
	}


	@Override
	public Throwable getException() {
		// TODO Auto-generated method stub
		return null;
	}


}
