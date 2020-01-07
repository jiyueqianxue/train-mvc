package io.mine.ft.train.moshi.zerenlian.emp_1.buf;

import java.util.Map;

public interface Buf {



    Buf put(String key, Object value);

    Map<String, Object> getContext();

    Object get(String key);

    Buf remove(String key);

    void clear();

    int size();

    Buf object2Buf(Object o);

    String toString();

    String toJsonString();

    Buf jsonString2Buf(String s);

    String getNextHandlerName();

    void setNextHandlerName(String nextHandlerName);

    Throwable getException();

    String getExceptionHandlerName();

}
