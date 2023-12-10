package com.scope.config.redis;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: juvenile
 * Date: 2019-02-28
 * Time: 10:52
 * Description:  RedisValues
 */
@Component
public class RedisValues {

    // 根据key设定具体的缓存时间
    private static Map<String, Long> expiresMap = new HashMap<>();
    public static final String tenMinute = "tenMinute";
    public static final String tenSec = "tenSec";
    public static final String oneHour = "oneHour";
    public static final String oneHalfHour = "oneHalfHour";
    public static final String oneDay = "oneDay";
    public static final String tenDay = "tenDay";
    public static final String OneMonth = "OneMonth";

    /**
     * 初始化  默认单位:秒
     */
    static {
        expiresMap.put(tenMinute, 60 * 10L);
        expiresMap.put(tenSec, 10L);
        expiresMap.put(oneHour, 60 * 60L);
        expiresMap.put(oneHalfHour, 110 * 60L);
        expiresMap.put(oneDay, 60 * 60 * 24L);
        expiresMap.put(tenDay, 60 * 60 * 24L * 10);
        expiresMap.put(OneMonth, 60 * 60 * 24L * 30);
    }

    public Map<String, Long> getExpiresMap() {
        return expiresMap;
    }
}

