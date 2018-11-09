package com.siyueli.platform.service.member.server.service.redis.impl;

import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;
import com.siyueli.platform.service.member.server.constant.RedisConstant;
import com.siyueli.platform.service.member.server.service.redis.PermissionLoginRedisService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class PermissionLoginRedisServiceImpl extends BaseRedisServiceImpl implements PermissionLoginRedisService {

    private static final String REDIS_KEY_LIST = RedisConstant.REDIS_KEY_PREFIX + "permissionLoginList";

    @Override
    public void saveList(List<PermissionLoginVo> list) {
        if (list != null && list.size() > 0) {
            try {
                setAndExpireObject(REDIS_KEY_LIST, list);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<PermissionLoginVo> getList() {
        try {
            List<PermissionLoginVo> list = getList(REDIS_KEY_LIST, PermissionLoginVo.class);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            checkTtl(REDIS_KEY_LIST);
        }
        return null;
    }

    @Override
    public void delList() {
        jedisCluster.del(REDIS_KEY_LIST);
    }
}
