package com.rt.base.business.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class GenerateSystemCodeUtil {

    public static String getSystemCode(RedisTemplate redisTemplate, String key){
        return getSystemCode(redisTemplate,key,getCurrent2TodayEndMillisTime());
    }


    public static String getSystemCode(RedisTemplate redisTemplate, String key, Long expire){
        String redisKey="systemCode:"+ key;
        Long incr = getIncr(redisTemplate,redisKey, expire);
        if(incr==0) {
            incr = getIncr(redisTemplate,redisKey, expire);//从00001开始
        }
        DecimalFormat df=new DecimalFormat("00000");//五位位序列号
        return String.format("%s_%s",key,df.format(incr));
    }

    public static Long getIncr(RedisTemplate redisTemplate, String key, long liveTime) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        Long increment = entityIdCounter.getAndIncrement();

        if ((null == increment || increment.longValue() == 0) && liveTime > 0) {//初始设置过期时间
            entityIdCounter.expire(liveTime, TimeUnit.MILLISECONDS);//单位毫秒
        }
        return increment;
    }

    //现在到今天结束的毫秒数
    public static Long getCurrent2TodayEndMillisTime() {
        Calendar todayEnd = Calendar.getInstance();
        // Calendar.HOUR 12小时制
        // HOUR_OF_DAY 24小时制
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis()- Calendar.getInstance().getTimeInMillis();
    }
}
