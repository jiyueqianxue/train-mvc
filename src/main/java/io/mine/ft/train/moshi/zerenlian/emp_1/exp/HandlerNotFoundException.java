package io.mine.ft.train.moshi.zerenlian.emp_1.exp;

/**
 * 获取不到指定handler时抛出
 * 
 * @author 
 *
 */
public class HandlerNotFoundException extends RuntimeException{

	/**
     * 
     */
    private static final long serialVersionUID = 5072685561864514373L;

    public HandlerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public HandlerNotFoundException(String message) {
		super(message);
	}

}
