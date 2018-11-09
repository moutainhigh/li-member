package com.siyueli.platform.service.member.server.service.permission.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.PermissionRole;
import com.siyueli.platform.service.member.server.mapper.permission.PermissionRoleMapper;
import com.siyueli.platform.service.member.server.service.permission.PermissionRoleServiceContract;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限角色对应表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PermissionRoleService extends ServiceImpl<PermissionRoleMapper, PermissionRole> implements PermissionRoleServiceContract {

    @Override
    public List<PermissionRole> getByRoleIds(List<Long> roleIds) {
        EntityWrapper<PermissionRole> entityWrapper = new EntityWrapper<PermissionRole>();
        entityWrapper.in("role_id", roleIds.toArray(new Long[roleIds.size()]));
        List<PermissionRole> list = selectList(entityWrapper);

        return list;
    }
}
