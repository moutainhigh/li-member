package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.member.MemberBusiness;
import com.siyueli.platform.service.member.server.mapper.member.MemberBusinessMapper;
import com.siyueli.platform.service.member.server.service.member.MemberBusinessServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商家员工表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberBusinessService extends ServiceImpl<MemberBusinessMapper, MemberBusiness> implements MemberBusinessServiceContract {

}
