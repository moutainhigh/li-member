package com.siyueli.platform.service.member.server.service.permission.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.PermissionAction;
import com.siyueli.platform.service.member.server.mapper.permission.PermissionActionMapper;
import com.siyueli.platform.service.member.server.service.permission.PermissionActionServiceContract;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PermissionActionService extends ServiceImpl<PermissionActionMapper, PermissionAction> implements PermissionActionServiceContract {

}
