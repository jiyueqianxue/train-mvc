package io.mine.ft.train.moshi.zerenlian.emp_1;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;

import io.mine.ft.train.moshi.zerenlian.emp_1.asyn.AsyncInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.BranchType;
import lombok.Data;
@Data
public class Branch {

	/**
	 * 分支策略
	 */
	private String rule;

	/**
	 * 扩展表达式
	 */
	private String extensionEl;
	/**
	 * 下一个handler名称
	 */
	private String nextHandlerName;
	/**
     * 分支类型
     *
     * @see BranchType
     */
    private BranchType branchType;

	/**
	 * 异步线程池
	 */
	private AsyncInfo asyncInfo;

	private boolean async = Boolean.FALSE;

	private String rpcUrl;

	private boolean rpc = Boolean.FALSE;

	

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getNextHandlerName() {
		return nextHandlerName;
	}

	public void setNextHandlerName(String nextHandlerName) {
		this.nextHandlerName = nextHandlerName;
	}

	public BranchType getBranchType() {
		return branchType;
	}

	public void setBranchType(BranchType branchType) {
		this.branchType = branchType;
	}

	public boolean isAsync() {
		return async;
	}

	public void init() {
		if (asyncInfo != null) {
			this.async = Boolean.TRUE;
		}
	}

	public boolean excRule(Map<String, Object> env) {
	    
	    if(StringUtils.isNotBlank(rule)){
            return null!=env&&env.containsKey(rule)&&env.containsValue(rule);
        }
		return false;
	}
	
	public String excExtensionEl(Map<String, Object> env){
		return null;
		
	}

	public AsyncInfo getAsyncInfo() {
		return asyncInfo;
	}

	public void setAsyncInfo(AsyncInfo asyncInfo) {
		this.asyncInfo = asyncInfo;
	}

	public String getAsyncWorkPool() {
		if (async)
			return asyncInfo.getAsyncWorkPool();
		return null;
	}

	public String getRpcUrl() {
		return rpcUrl;
	}

	public boolean isRpc() {
		return rpc;
	}

	public void setRpcUrl(String rpcUrl) {
		this.rpcUrl = rpcUrl;
	}

	public void setRpc(boolean rpc) {
		this.rpc = rpc;
	}

	public String getExtensionEl() {
		return extensionEl;
	}

	public void setExtensionEl(String extensionEl) {
		this.extensionEl = extensionEl;
	}
	
}
