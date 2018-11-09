package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.member.MemberOperator;
import com.siyueli.platform.service.member.server.mapper.member.MemberOperatorMapper;
import com.siyueli.platform.service.member.server.service.member.MemberOperatorServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运营人员表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberOperatorService extends ServiceImpl<MemberOperatorMapper, MemberOperator> implements MemberOperatorServiceContract {

}
