package com.siyueli.platform.service.member.server.service.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;

import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 会员等级表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface MemberGradeServiceContract extends IService<MemberGrade> {

  /**
   * 新建等级
   */
  ResponseData addGrade(SaveMemberGradeRequest request);

  /**
   * 修改等级
   */
  ResponseData updateGrade(Long gradeId,UpdateMemberGradeRequest request);

  /**
   * 查找所有等级
   * @param page 页码
   * @param size 大小
   */
  ResponseData<Page> searchAllGrade(int page, int size);

  /**
   * 删除等级
   */
  ResponseData deleteGradeById(Long gradeId);

}
