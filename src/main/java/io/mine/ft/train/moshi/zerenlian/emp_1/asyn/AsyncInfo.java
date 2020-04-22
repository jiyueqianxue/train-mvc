package io.mine.ft.train.moshi.zerenlian.emp_1.asyn;


public class AsyncInfo  {

	/**
	 * 异步线程池
	 */
	private String asyncWorkPool;

	/**
	 * ringbuffer大小
	 */
	private Integer bufferSize;

	/**
	 * workThreadNum线程数
	 */
	private Integer workThreadNum;

	public AsyncInfo(String asyncWorkPool, Integer workThreadNum) {
		this.asyncWorkPool = asyncWorkPool;
		this.workThreadNum = workThreadNum;
	}

	public String getAsyncWorkPool() {
		return asyncWorkPool;
	}

	public void setAsyncWorkPool(String asyncWorkPool) {
		this.asyncWorkPool = asyncWorkPool;
	}

	public Integer getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(Integer bufferSize) {
		this.bufferSize = bufferSize;
	}

	public Integer getWorkThreadNum() {
		return workThreadNum;
	}

	public void setWorkThreadNum(Integer workThreadNum) {
		this.workThreadNum = workThreadNum;
	}

	@Override
	public String toString() {
		return "asyncWorkPool【" + asyncWorkPool + "】bufferSize【" + bufferSize + "】workThreadNum【" + workThreadNum + "】";
	}
}
