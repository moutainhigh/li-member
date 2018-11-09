/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.grade;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;
import com.siyueli.platform.service.member.client.service.grade.MemberGradeService;
import org.springframework.stereotype.Component;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 等级熔断器
 */
@Component
public class MemberGradeServiceFallBack implements MemberGradeService {

  @Override public ResponseData addGrade(SaveMemberGradeRequest request) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override public ResponseData updateGrade(Long gradeId, UpdateMemberGradeRequest request) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override
  public ResponseData deleteGrade(Long id) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());

  }

  @Override
  public ResponseData<Page> searchAllGrade(int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
