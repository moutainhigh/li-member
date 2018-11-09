package com.siyueli.platform.service.member.server.service.permission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.PermissionGroup;
import com.siyueli.platform.service.member.server.mapper.permission.PermissionGroupMapper;
import com.siyueli.platform.service.member.server.service.permission.PermissionGroupServiceContract;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限分组 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PermissionGroupService extends ServiceImpl<PermissionGroupMapper, PermissionGroup> implements PermissionGroupServiceContract {

}
