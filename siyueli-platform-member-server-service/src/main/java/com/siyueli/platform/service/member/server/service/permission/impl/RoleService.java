package com.siyueli.platform.service.member.server.service.permission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.Role;
import com.siyueli.platform.service.member.server.mapper.permission.RoleMapper;
import com.siyueli.platform.service.member.server.service.permission.RoleServiceContract;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class RoleService extends ServiceImpl<RoleMapper, Role> implements RoleServiceContract {

}
