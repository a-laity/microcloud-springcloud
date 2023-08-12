package com.imooc.tcc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//TCC状态存储
public class TccResultStore {
    private static final Map<Class<?>, Map<String, String>> map = new ConcurrentHashMap<>();

    public static void setResultStore(Class<?> tccClass, String xid, String v) {
        Map<String, String> result = map.get(tccClass);
        if (result == null) {
            synchronized (map) {
                if (result == null) {
                    result = new ConcurrentHashMap<>();
                    map.put(tccClass, result);
                }
            }
        }
        result.put(xid, v);
    }

    public static String getResultStore(Class<?> tccClass, String xid) {
        Map<String, String> result = map.get(tccClass);
        if (result != null) {
            return result.get(xid);
        }
        return null;
    }
    public static void revomeResultStore(Class<?> tccClass, String xid) {
        Map<String, String> result = map.get(tccClass);
        if (result != null) {
           result.remove(xid);
        }

    }
}
