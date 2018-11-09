/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.controller.grade;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.grade.addGrade.SaveMemberGradeRequest;
import com.siyueli.platform.member.request.grade.updateGrade.UpdateMemberGradeRequest;
import com.siyueli.platform.service.member.client.service.grade.MemberGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 权益管理类
 */
@Api(tags = "斯越里_后台_等级管理接口")
@RequestMapping(path = "/member/grade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberGradeController {

  private MemberGradeService memberGradeService;

  @Autowired
  public MemberGradeController(MemberGradeService memberGradeService){
    this.memberGradeService = memberGradeService;
  }

  /*
   * 新增等级
   */
  @ApiOperation(nickname = "memberGradeAddGrade",value = "新增等级接口")
  @PostMapping("/addGrade")
  public ResponseData addGrade(@RequestBody @Valid SaveMemberGradeRequest request, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                                ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors());
    }
    return memberGradeService.addGrade(request);
  }


  /*
   * 修改等级
   */
  @ApiOperation(nickname = "memberGradeUpdateGrade",value = "修改等级接口")
  @PutMapping("/updateGrade/{gradeId}")
  public ResponseData updateGrade(@PathVariable Long gradeId,
                                  @RequestBody @Valid UpdateMemberGradeRequest request, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberGradeService.updateGrade(gradeId,request);
  }

  /*
   * 删除等级
   */
  @ApiOperation(nickname = "memberGradeDeleteGrade",value = "删除等级接口")
  @PutMapping("/deleteGrade/{gradeId}")
  public ResponseData deleteGrade(@PathVariable Long gradeId) {
    return memberGradeService.deleteGrade(gradeId);
  }

  /**
   * 查找所有等级
   */
  @ApiOperation(nickname = "memberGradeSearchAllGrade",value = "查找所有等级")
  @GetMapping("/searchAllGrade")
  public ResponseData<Page> searchAllGrade(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return memberGradeService.searchAllGrade(page, size);
  }
}
