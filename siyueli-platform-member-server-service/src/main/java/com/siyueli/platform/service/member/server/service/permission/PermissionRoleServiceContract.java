package com.siyueli.platform.service.member.server.service.permission;

import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.PermissionRole;

import java.util.List;

/**
 * <p>
 * 权限角色对应表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface PermissionRoleServiceContract extends IService<PermissionRole> {


    public List<PermissionRole> getByRoleIds(List<Long> roleIds);
}
