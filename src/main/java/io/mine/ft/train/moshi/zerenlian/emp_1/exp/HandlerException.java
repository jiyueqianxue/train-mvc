package io.mine.ft.train.moshi.zerenlian.emp_1.exp;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HandlerException extends RuntimeException {

    /**
     * 
     */
    private static final long   serialVersionUID = -1653545139857624667L;

    private final static String MESSAGE          = " Handler  exception";

    private HandlerContext<?>      handlerContext;

    public HandlerException(HandlerContext<?> handlerContext, Throwable cause) {
        super(MESSAGE, cause);
        handlerContext.setHandlerResult(HandleResult.FAIL);
        this.handlerContext = handlerContext;
    }

    public HandlerException(HandlerContext<?> handlerContext, String message, Throwable cause) {
        super(message, cause);
        handlerContext.setHandlerResult(HandleResult.FAIL);
        this.handlerContext = handlerContext;
    }

    public HandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public HandlerException(String message) {
        super(message);
    }

    public HandlerException(Throwable cause) {
        super(cause);
    }

    public HandlerContext<?> getHandlerContext() {
        return handlerContext;
    }

    public HandlerException clear() {
        this.handlerContext = null;
        return this;
    }
}
