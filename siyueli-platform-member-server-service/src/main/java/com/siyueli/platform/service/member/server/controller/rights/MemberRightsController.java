/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.rights;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.member.pojo.address.MemberAddress;
import com.siyueli.platform.member.pojo.rights.MemberRights;
import com.siyueli.platform.member.request.rights.addRights.SaveMemberRightsRequest;
import com.siyueli.platform.member.request.rights.updateRights.UpdateMemberRightsRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.member.response.rights.common.MemberRightsResponse;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.member.MemberRightsServiceContract;

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
 * 斯越里权益管理类
 */
@RequestMapping(path = "/member/rights", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberRightsController {

  private MemberRightsServiceContract memberRightsService;

  @Autowired
  public MemberRightsController(MemberRightsServiceContract memberRightsService){
    this.memberRightsService = memberRightsService;
  }

  /*
   * 新增权益
   */
  @PostMapping("/addRights")
  public ResponseData addRights(@RequestBody SaveMemberRightsRequest request) {
    MemberRights rights = new MemberRights();
    BeanUtils.copyProperties(request, rights);
    memberRightsService.insert(rights);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());

  }

  /*
   * 修改权益
   */
  @PutMapping("/updateRights/{rightId}")
  public ResponseData updateRights(@PathVariable Long rightId, @RequestBody UpdateMemberRightsRequest request) {
    MemberRights rights = new MemberRights();
    BeanUtils.copyProperties(request,rights);
    rights.setId(rightId);
    memberRightsService.updateById(rights);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /*
   * 设置权益状态
   */
  @PutMapping("/setStatus/{rightId}")
  public ResponseData setStatus(@PathVariable Long rightId) {
    MemberRights memberRights = memberRightsService.selectById(rightId);
    if(memberRights.getStatus() == MemberConstants.STATUS_VALID){
      memberRights.setStatus(MemberConstants.STATUS_INVALID);
    }else{
      memberRights.setStatus(MemberConstants.STATUS_VALID);
    }
    memberRightsService.updateById(memberRights);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 查找单个权益
   */
  @GetMapping("/searchSingleRights/{rightId}")
  public ResponseData<MemberRightsResponse> searchSingleRights(@PathVariable Long rightId) {
    MemberRights memberRights = memberRightsService.selectById(rightId);
    MemberRightsResponse response = new MemberRightsResponse();
    BeanUtils.copyProperties(memberRights,response);
    response.setId(Long.toString(rightId));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), response);
  }

  /**
   * 查找所有权益
   */
  @GetMapping("/searchAllRights")
  public ResponseData<Page> searchAllRights(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    Page userPage = new Page<MemberRights>(page, size);
    userPage.setAsc(false);
    userPage = memberRightsService.selectPage(userPage, new EntityWrapper<>());
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

}
