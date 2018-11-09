package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.rights.MemberRights;
import com.siyueli.platform.service.member.server.mapper.member.MemberRightsMapper;
import com.siyueli.platform.service.member.server.service.member.MemberRightsServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权益表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberRightsService extends ServiceImpl<MemberRightsMapper, MemberRights> implements MemberRightsServiceContract {

  /**
   * 通过等级id获取权益
   * @param gradeId 等级id
   */
  @Override
  public List<MemberRights> getRightsByGradeId(Long gradeId) {
    return baseMapper.getRightsByGradeId(gradeId);
  }
}
