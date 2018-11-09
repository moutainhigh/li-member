package com.siyueli.platform.service.member.server.service.permission;

import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.UserRole;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface UserRoleServiceContract extends IService<UserRole> {

    public List<UserRole> getByUserId(Long userId);

}
