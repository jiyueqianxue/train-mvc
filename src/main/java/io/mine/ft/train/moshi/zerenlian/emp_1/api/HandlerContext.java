package io.mine.ft.train.moshi.zerenlian.emp_1.api;

import java.util.List;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.buf.Buf;
import io.mine.ft.train.moshi.zerenlian.emp_1.engine.EnginePipeline;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.Instruction;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;

public interface HandlerContext<I> {

    /**
     * 获取handler内部域
     *
     * @return
     */
    I getInnerParam();

    /**
     * 设置handler内部域
     *
     * @param innerParam
     */
    void setInnerParam(I innerParam);

    /**
     * 获取下一个handlerContext
     *
     * @return
     */
    HandlerContext<?> getNext();

    /**
     * 设置下一个handlerContext
     *
     * @param nextHandlercontext
     * @return
     */
    HandlerContext<?> setNext(HandlerContext<?> nextHandlercontext);

    /**
     * 获取handlerName
     *
     * @return
     */
    String name();

    /**
     * 获取下一个handlerName
     *
     * @return
     */
    String getNextHandlerName();

    /**
     * 获取当前handler执行结果
     *
     * @return
     * @see HandleResult
     */
    HandleResult getHandlerResult();

    /**
     * 设置当前handler处理结果
     *
     * @param handleResult
     */
    void setHandlerResult(HandleResult handleResult);

    /**
     * 获取当前handlerContext的handler
     *
     * @return
     */
    Handler<I> getHandler();

    /**
     * 设置当前handlerContext的handler
     *
     * @param handler
     * @return
     */
    HandlerContext<I> setHandler(Handler<I> handler);

    /**
     * 根据handlerName获取handlerContext
     *
     * @param handlerName
     * @return
     */
    HandlerContext<?> getHandlerContext(String handlerName);

    /**
     * 获取handler信息
     *
     * @return
     * @see EngineInfo
     */
    EngineInfo getEngineInfo();

    /**
     * 设置handler信息
     *
     * @param EngineInfo
     * @return
     */
    HandlerContext<I> setEngineInfo(EngineInfo engineInfo);

    /**
     * 获取最后一个handlerContext
     *
     * @return
     */
    HandlerContext<?> getLast();

    /**
     * 触发异常处理责任链
     *
     * @param cause
     */
    void fireExceptionCaught(EngineException cause);

    /**
     * 获取剩余handlercontext的EngineInfo
     * 
     * @param routerInfos
     * @return
     */
    List<EngineInfo> getNextRouterInfos(List<EngineInfo> engineInfos);

    /**
     * 责任链调用
     * 
     * @return
     */
    Buf invokeHandler();

    /**
     * 当前handler调用
     */
    Instruction invokeCurrentHandler();

    /**
     * 获取容器
     *
     * @return
     */
    EnginePipeline<?, ?> getEngine();

    boolean isRpc();

    /**
     * 获取责任链的buf
     *
     * @return
     */
    Buf getBuf();

    /**
     * 替换buf
     *
     * @return
     */
    Buf setNewBuf(Buf buf);

    /**
     * 填充buf键值对
     *
     * @param key
     * @param value
     * @return
     */
    Buf putBuf(String key, Object value);

    /**
     * 是否需要调下一个handler
     * 不建议使用，请优先使用分支配置模式
     * @param isNext
     */
    @Deprecated
    void isNext(Boolean isNext);
    @Deprecated
    Boolean isNext();

    /**
     * 执行序号* 不建议使用，请优先使用分支配置模式
     */
    @Deprecated
    default void  appointOrder(Integer order) {
    };
    @Deprecated
    default Integer  getOrder() {
        return null;
    };
}
