/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.controller.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateBackendMemberUserRequest;
import com.siyueli.platform.service.member.client.service.member.MemberBackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "斯越里_后台_会员管理接口")
@RequestMapping(path = "/member/backend/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberBackendUserController {

  private MemberBackendUserService memberBackendUserService;

  @Autowired
  public MemberBackendUserController(MemberBackendUserService memberBackendUserService){
    this.memberBackendUserService = memberBackendUserService;
  }


  @ApiOperation(nickname = "memberBackendDeleteAllUser",value = "删除多个会员")
  @PutMapping("/deleteAllUser")
  public ResponseData updateUser(@RequestBody List<Long> userIds) {
    return memberBackendUserService.deleteAllUser(userIds);
  }

  @ApiOperation(nickname = "memberBackendDeleteUser",value = "删除单个会员接口")
  @PutMapping("/deleteUser/{userId}")
  public ResponseData deleteUser(@PathVariable Long userId) {
    return memberBackendUserService.deleteUser(userId);
  }

  @ApiOperation(nickname = "memberBackendSearchAllUser",value = "查找所有会员")
  @GetMapping("/searchAllUser")
  public ResponseData<Page> searchAllUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @Valid SearchAllUserRequest request,BindingResult result) {
    if (result.hasErrors()) {
      return new ResponseData<>(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),result.getFieldError().getDefaultMessage());
    }
      return memberBackendUserService.searchAllUser(page, size,request);
  }

  @ApiOperation(nickname = "memberBackendUpdateUser",value = "修改单个会员接口")
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId,
                                 @RequestBody @Valid UpdateBackendMemberUserRequest updateBackendMemberUserRequest,
                                 BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberBackendUserService.updateUser(userId,updateBackendMemberUserRequest);
  }

  @ApiOperation(nickname = "memberBackendToken",value = "验证token")
  @PostMapping(value = "/token")
  public ResponseData token() {
    return memberBackendUserService.token();
  }

  @ApiOperation(nickname = "memberBackendSearchUser",value = "查找单个会员")
  @GetMapping("/searchUser/{userId}")
  public ResponseData searchUser(@PathVariable Long userId) {
    return memberBackendUserService.searchUser(userId);
  }

  @ApiOperation(nickname = "memberBackendUserLogin",value = "后台登录")
  @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
  public ResponseData userLogin(@RequestBody @Valid BackendUserLoginRequest backendUserLoginRequest
  ,BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberBackendUserService.userLogin(backendUserLoginRequest);
  }

  /**
   * 修改余额
   */
  @ApiOperation(nickname = "memberBackendUpdateBalance",value = "修改余额")
  @PutMapping(value = "/updateBalance/{userId}")
  public ResponseData updateBalance(@PathVariable Long userId,
                                   @RequestBody @Valid UpdateBalanceRequest request,
                                   BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberBackendUserService.updateBalance(userId,request);
  }

  /**
   * 修改积分
   */
  @ApiOperation(nickname = "memberBackendUpdatePoints",value = "修改积分")
  @PutMapping(value = "/updatePoints/{userId}")
  public ResponseData updatePoints(@PathVariable Long userId,
                                   @RequestBody @Valid UpdateBalanceRequest request,
                                   BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberBackendUserService.updatePoints(userId,request);
  }

  /**
   * 查找余额记录
   */
  @ApiOperation(nickname = "memberBackendSearchBalance",value = "查找余额记录")
  @GetMapping(value = "/searchBalance/{userId}")
  public ResponseData<Page> searchBalance(@PathVariable Long userId,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "15") int size){
    return memberBackendUserService.searchBalance(userId,page,size);
  }

  /**
   * 查找积分记录
   */
  @ApiOperation(nickname = "memberBackendSearchPoints",value = "查找积分记录")
  @GetMapping(value = "/searchPoints/{userId}")
  public ResponseData<Page> searchPoints(@PathVariable Long userId,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "15") int size){
    return memberBackendUserService.searchPoints(userId,page,size);
  }
}
