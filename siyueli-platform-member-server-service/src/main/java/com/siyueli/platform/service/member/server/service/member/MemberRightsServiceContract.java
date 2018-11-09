package com.siyueli.platform.service.member.server.service.member;

import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.rights.MemberRights;

import java.util.List;

/**
 * <p>
 * 权益表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface MemberRightsServiceContract extends IService<MemberRights> {

  /**
   * 通过等级id获取相关的权益数据
   * @param gradeId 等级id
   */
  List<MemberRights> getRightsByGradeId(Long gradeId);

}
