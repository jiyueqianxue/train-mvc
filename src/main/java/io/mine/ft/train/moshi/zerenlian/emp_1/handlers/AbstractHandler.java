package io.mine.ft.train.moshi.zerenlian.emp_1.handlers;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.hutool.json.JSONUtil;
import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.HandlerInfo;
import io.mine.ft.train.moshi.zerenlian.emp_1.HandlerManager;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.Handler;
import io.mine.ft.train.moshi.zerenlian.emp_1.api.HandlerContext;
import io.mine.ft.train.moshi.zerenlian.emp_1.enums.HandleResult;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.EngineException;
import io.mine.ft.train.moshi.zerenlian.emp_1.exp.HandlerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractHandler<I> implements Handler<I> {

    protected HandlerInfo handlerInfo;

    @Override
    public void beforeHandle(HandlerContext<I> hc) {

    }

    @SuppressWarnings("unchecked")
    @Override
    public void genInnerParam(HandlerContext<I> hc) {

        if (null == hc.getInnerParam()) {
            Map<String, Object> buf = hc.getEngine().getBuf().getContext();
            String className = hc.getEngineInfo().getHandlerInfo().getHandlerClass();
            String convertStr = hc.getEngineInfo().getHandlerInfo().getConvertStr();
            try {
                if (StringUtils.isNotBlank(convertStr)) {
                    Map<String, Object> params = JSON.parseObject(convertStr);
                    params.forEach((k, v) -> {
                        if (k.contains(".")) {
                            log.info("获取JSON中嵌套 key{}", k);
                            cn.hutool.json.JSONObject jb = JSONUtil.parseFromMap(buf);
                            
                            jb.getByPath(k);
                            
                            if (ObjectUtils.allNotNull(jb.getByPath(k))) {
                                buf.put(v.toString(), jb.getByPath(k));
                            }

                        } else if (ObjectUtils.allNotNull(buf.get(k))) {
                            buf.put(v.toString(), buf.get(k));
                        }

                    });
                }
                Object object = JSON.parseObject(JSON.toJSONString(buf), HandlerManager.getDtoClass(className));
                hc.setInnerParam((I) object);
            } catch (NumberFormatException ne) {
                log.error("InnerParam  NumberFormatException  dtoClassName:{}", className, ne);
                throw new HandlerException("数据格式不正确");
            }
        }
    }

    @Override
    public void exceptionCaught(EngineException cause) {
    }

    @Override
    public void handleCompleted(HandlerContext<I> hc) {
        hc.setHandlerResult(HandleResult.SUCESS);
        final EngineInfo engineInfo = hc.getEngineInfo();
        if (null != engineInfo) {
            String engineName = engineInfo.getName();
            List<EngineInfo> engineInfos = hc.getEngine().getEngineInfos(engineName);

            log.info("invoke {} routerName{} completed ", this.getClass().getSimpleName(), engineName);

            if (CollectionUtils.isNotEmpty(engineInfos)) {
                log.info("{}-{} completed", engineInfos.size(), this.getClass().getSimpleName());
            }

            hc.setInnerParam(null);
            log.info("clear inner param of next handler:{}, after clear:{}", hc.getEngineInfo().getName(), hc.getInnerParam());
        }
    }

    @Override
    public HandlerInfo getHandlerInfo() {
        return handlerInfo;
    }

    @Override
    public void setHandlerInfo(HandlerInfo handlerInfo) {
        this.handlerInfo = handlerInfo;
    }

    @Override
    public String toString() {
        return handlerInfo.toString();
    }

}
