package io.mine.ft.train.moshi.zerenlian.emp_1.engine;

import java.util.List;

import io.mine.ft.train.moshi.zerenlian.emp_1.EngineInfo;

public interface EngineConfig {

    List<EngineInfo> getEngineInfos(String engineName);

}
