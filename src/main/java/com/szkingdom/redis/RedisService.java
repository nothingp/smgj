package com.szkingdom.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Lange
 * @date 2018-11-05 17:48
 */
@Component
public class RedisService<HK, V> {

    private RedisTemplate<String, V> redisTemplate;
    private HashOperations<String, HK, V> hashOperations;
    private ListOperations<String, V> listOperations;
    private ZSetOperations<String, V> zSetOperations;
    private SetOperations<String, V> setOperations;
    private ValueOperations<String, V> valueOperations;

    @Autowired
    public RedisService(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
        this.zSetOperations = redisTemplate.opsForZSet();
        this.setOperations = redisTemplate.opsForSet();
        this.valueOperations = redisTemplate.opsForValue();
    }


    public void hashPut(String key, HK hashKey, V value) {
        hashOperations.put(key, hashKey, value);
    }

    public Map<HK, V> hashFindAll(String key) {
        return hashOperations.entries(key);
    }

    public V hashGet(String key, HK hashKey) {
        return hashOperations.get(key, hashKey);
    }

    public void hashRemove(String key, HK hashKey) {
        hashOperations.delete(key, hashKey);
    }

    public Long listPush(String key, V value) {
        return listOperations.rightPush(key, value);
    }

    public Long listUnshift(String key, V value) {
        return listOperations.leftPush(key, value);
    }

    public List<V> listFindAll(String key) {
        if (!redisTemplate.hasKey(key)) {
            return null;
        }
        return listOperations.range(key, 0, listOperations.size(key));
    }

    public V listLPop(String key) {
        return listOperations.leftPop(key);
    }

    public void setValue(String key, V value) {
        valueOperations.set(key, value);
    }

    public void setValue(KeyPrefix kp, String key, V value) {
        Long timeout = kp .getTimeout();
        if(timeout == null || timeout <= 0){
            this.setValue(kp.getPrefix() + key, value);
        }else{
            this.setValue(kp.getPrefix() + key, value, kp.getTimeout());
        }
    }

    public void setValue(String key, V value, long timeout) {
        valueOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
    }


    public V getValue(String key) {
        return valueOperations.get(key);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return redisTemplate.expire(key, timeout, timeUnit);
    }

}
