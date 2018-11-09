package com.siyueli.platform.service.member.server.service.member.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.rights.MemberRightsGrade;
import com.siyueli.platform.service.member.server.mapper.member.MemberRightsGradeMapper;
import com.siyueli.platform.service.member.server.service.member.MemberRightsGradeServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权益等级表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberRightsGradeService extends ServiceImpl<MemberRightsGradeMapper, MemberRightsGrade> implements MemberRightsGradeServiceContract {

}
