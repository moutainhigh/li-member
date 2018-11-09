package com.siyueli.platform.service.member.server.mapper.member;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.siyueli.platform.member.pojo.rights.MemberRights;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权益表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface MemberRightsMapper extends BaseMapper<MemberRights> {

  List<MemberRights> getRightsByGradeId(@Param("gradeId") Long gradeId);


}
