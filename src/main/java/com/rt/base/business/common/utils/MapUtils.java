package com.rt.base.business.common.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Map工具类
 *
 * @author Mark initPlatform
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
    /**
     * map 按照key排序
     * @param sort
     * @return
     */
    public static Map<String, Integer> sortMap(Map<String, Integer> sort){
        Map<String, Integer> result = new LinkedHashMap<>();
        //按照Key进行排序
        sort.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));
        return result;
    }
}
