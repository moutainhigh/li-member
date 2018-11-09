/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.controller.rights;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.rights.MemberRights;
import com.siyueli.platform.member.request.rights.addRights.SaveMemberRightsRequest;
import com.siyueli.platform.member.request.rights.updateRights.UpdateMemberRightsRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.member.response.rights.common.MemberRightsResponse;
import com.siyueli.platform.service.member.client.service.rights.MemberRightsService;
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
@Api(tags = "斯越里_后台_权益管理接口")
@RequestMapping(path = "/member/rights", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberRightsController {

  private MemberRightsService memberRightsService;

  @Autowired
  public MemberRightsController(MemberRightsService memberRightsService){
    this.memberRightsService = memberRightsService;
  }

  /*
   * 新增权益
   */
  @ApiOperation(nickname = "memberRightsAddRights",value = "新增权益接口")
  @PostMapping("/addRights")
  public ResponseData addRights(@RequestBody @Valid SaveMemberRightsRequest request,
                                BindingResult result) {
    if (result.hasErrors()) {
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
                                ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors());
    }
    return memberRightsService.addRights(request);
  }


  /*
   * 修改权益
   */
  @ApiOperation(nickname = "memberRightsUpdateRights",value = "修改权益接口")
  @PutMapping("/updateRights/{rightId}")
  public ResponseData updateRights(@PathVariable Long rightId, @RequestBody @Valid UpdateMemberRightsRequest memberRights,
                                   BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberRightsService.updateRights(rightId,memberRights);
  }

  /*
   * 设置权益状态
   */
  @ApiOperation(nickname = "memberRightsForbiddenRights",value = "设置权益状态接口")
  @PutMapping("/setStatus/{rightId}")
  public ResponseData setStatus(@PathVariable Long  rightId) {
    return memberRightsService.setStatus(rightId);
  }

  @ApiOperation(nickname = "memberRightsSearchSingleRights",value = "查找单个权益")
  @GetMapping("/searchSingleRights/{rightId}")
  public ResponseData<MemberRightsResponse> searchSingleRights(@PathVariable Long  rightId) {
    return memberRightsService.searchSingleRights(rightId);
  }

  @ApiOperation(nickname = "memberRightsSearchAllRights",value = "查找所有权益")
  @GetMapping("/searchAllRights")
  public ResponseData<Page> searchAllRights(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
      return memberRightsService.searchAllRights(page, size);
  }
}
