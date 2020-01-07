package io.mine.ft.train.moshi.zerenlian.emp_1.exp;

import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EngineException extends RuntimeException {

    /**
     * 
     */
    private static final long   serialVersionUID = 365083240991408960L;

    private final static String MESSAGE = " Engine  exception";

    private HandlerContext<?> handlerContext;

    public EngineException(HandlerContext<?> handlerContext, Throwable cause) {
        super(MESSAGE, cause);
        handlerContext.setHandlerResult(HandleResult.FAIL);
        this.handlerContext = handlerContext;
    }

    public EngineException(HandlerContext<?> handlerContext, String message, Throwable cause) {
        super(message, cause);
        handlerContext.setHandlerResult(HandleResult.FAIL);
        this.handlerContext = handlerContext;
    }

    public EngineException(String message, Throwable cause) {
        super(message, cause);
    }

    public EngineException(String message) {
        super(message);
    }

    public EngineException(Throwable cause) {
        super(cause);
    }

    public HandlerContext<?> getHandlerContext() {
        return handlerContext;
    }

    public EngineException clear() {
        this.handlerContext = null;
        return this;
    }
}
