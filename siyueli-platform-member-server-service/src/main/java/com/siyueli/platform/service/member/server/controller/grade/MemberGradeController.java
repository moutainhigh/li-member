/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.grade;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.address.MemberAddress;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.member.MemberGradeServiceContract;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 斯越里等级管理类
 */
@RequestMapping(path = "/member/grade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberGradeController {

  private MemberGradeServiceContract memberGradeService;

  @Autowired
  public MemberGradeController(MemberGradeServiceContract memberGradeService){
    this.memberGradeService = memberGradeService;
  }

  /*
   * 新建等级
   */
  @PostMapping("/addGrade")
  public ResponseData addGrade(@RequestBody SaveMemberGradeRequest request) {
    return memberGradeService.addGrade(request);
  }

  /*
   * 修改等级
   */
  @PostMapping("/updateGrade/{gradeId}")
  public ResponseData updateGrade(@PathVariable Long gradeId,@RequestBody UpdateMemberGradeRequest request) {
    return  memberGradeService.updateGrade(gradeId,request);
  }

  /*
   * 删除等级
   */
  @PutMapping("/deleteGrade/{gradeId}")
  public ResponseData deleteGrade(@PathVariable Long gradeId) {
    return memberGradeService.deleteGradeById(gradeId);
  }

  /**
   * 查找所有等级
   */
  @GetMapping("/searchAllGrade")
  public ResponseData<Page> searchAllGrade(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    return memberGradeService.searchAllGrade(page,size);
  }

}
