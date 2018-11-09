package com.siyueli.platform.service.member.server.service.redis.impl;

import cn.siyue.platform.util.JsonUtil;
import com.siyueli.platform.service.member.server.constant.RedisConstant;
import com.siyueli.platform.service.member.server.service.redis.BaseRedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.List;

@Service
public class BaseRedisServiceImpl implements BaseRedisService {

    @Autowired
    protected JedisCluster jedisCluster;

    @Override
    public void checkTtl(String key) {
        checkTtl(key, RedisConstant.REDIS_EXPIRED_TIME);
    }

    @Override
    public void checkTtl(String key, int seconds) {
        Long ttl = jedisCluster.ttl(key);
        if (ttl == -1) {
            jedisCluster.expire(key, seconds);
        }
    }

    @Override
    public void setAndExpireString(String key, String content) {
        setAndExpireString(key, content, RedisConstant.REDIS_EXPIRED_TIME);
    }

    @Override
    public void setAndExpireObject(String key, Object object) throws IOException {
        setAndExpireObject(key, object, RedisConstant.REDIS_EXPIRED_TIME);
    }

    @Override
    public void setAndExpireString(String key, String content, int seconds) {
        jedisCluster.set(key, content);
        jedisCluster.expire(key, seconds);
    }

    @Override
    public void setAndExpireObject(String key, Object object, int seconds) throws IOException {
        String content = JsonUtil.toJsonString(object);
        setAndExpireString(key, content, seconds);
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) throws IOException {
        String jsonContent = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(jsonContent)) {
            T obj = JsonUtil.jsonToObject(jsonContent, clazz);
            return obj;
        }

        return null;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clazz) throws IOException {
        String jsonContent = jedisCluster.get(key);
        if (StringUtils.isNotEmpty(jsonContent)) {
            List<T> list = JsonUtil.jsonToObject(jsonContent, List.class, clazz);
            return list;
        }

        return null;
    }
}
