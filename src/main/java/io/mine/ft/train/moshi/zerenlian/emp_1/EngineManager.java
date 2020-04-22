package io.mine.ft.train.moshi.zerenlian.emp_1;
/**
 * 
 * 类EngineManager.java的实现描述：引擎管理 
 * @author  2019年3月27日 上午11:22:49
 */

import java.util.concurrent.ConcurrentHashMap;

public class EngineManager {

    /**
     * 引擎集合
     */
    private ConcurrentHashMap<String, EngineInfo>  engineMap = new ConcurrentHashMap<String, EngineInfo>();

    public  int size() {
        return engineMap.size();
    }

}
