package com.siyueli.platform.service.member.server.service.permission.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.UserRole;
import com.siyueli.platform.service.member.server.mapper.permission.UserRoleMapper;
import com.siyueli.platform.service.member.server.service.permission.UserRoleServiceContract;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleServiceContract {

    @Override
    public List<UserRole> getByUserId(Long userId) {
        EntityWrapper<UserRole> entityWrapper = new EntityWrapper<UserRole>();
        entityWrapper.eq("user_id", userId);
        List<UserRole> list = selectList(entityWrapper);
        return list;
    }
}
