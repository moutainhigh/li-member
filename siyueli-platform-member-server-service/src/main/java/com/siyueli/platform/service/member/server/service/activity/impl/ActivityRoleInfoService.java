package com.siyueli.platform.service.member.server.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.activity.ActivityRoleInfo;
import com.siyueli.platform.service.member.server.mapper.activity.ActivityRoleInfoMapper;
import com.siyueli.platform.service.member.server.service.activity.ActivityRoleInfoServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 规则信息表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class ActivityRoleInfoService extends ServiceImpl<ActivityRoleInfoMapper, ActivityRoleInfo> implements ActivityRoleInfoServiceContract {

}
