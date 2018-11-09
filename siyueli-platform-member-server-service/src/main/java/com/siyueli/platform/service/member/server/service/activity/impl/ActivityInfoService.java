package com.siyueli.platform.service.member.server.service.activity.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.activity.ActivityInfo;
import com.siyueli.platform.service.member.server.mapper.activity.ActivityInfoMapper;
import com.siyueli.platform.service.member.server.service.activity.ActivityInfoServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 活动基础信息表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class ActivityInfoService extends ServiceImpl<ActivityInfoMapper, ActivityInfo> implements ActivityInfoServiceContract {

}
