/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.controller.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.member.registerUser.RegisterUserRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import com.siyueli.platform.member.request.member.userLogin.UserLoginRequest;
import com.siyueli.platform.service.member.client.service.member.MemberUserService;
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

@Api(tags = "斯越里_前台_会员管理接口")
@RequestMapping(path = "/member/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberUserController {

  private MemberUserService memberUserService;

  @Autowired
  public MemberUserController(MemberUserService memberUserService){
    this.memberUserService = memberUserService;
  }

  @ApiOperation(nickname = "memberUserRegisterUser",value = "注册会员接口")
  @PostMapping("/registerUser")
  public ResponseData registerUser(@RequestBody @Valid RegisterUserRequest registerUserRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberUserService.registerUser(registerUserRequest);
  }

  @ApiOperation(nickname = "memberUserUserLogin",value = "登录")
  @PostMapping("/userLogin")
  public ResponseData userLogin(@RequestBody @Valid UserLoginRequest userLoginRequest) {
    return memberUserService.userLogin(userLoginRequest);
  }

  @ApiOperation(nickname = "memberUserUserLogout",value = "登出")
  @PostMapping(value = "/userLogout")
  public ResponseData userLogout() {
    return memberUserService.userLogout();
  }

  @ApiOperation(nickname = "memberUserGetUserInfo",value = "查找用户信息")
  @GetMapping(value = "/getUserInfo")
  public ResponseData getUserInfo() {
    return memberUserService.getUserInfo();
  }

  @ApiOperation(nickname = "memberUserUpdateUser",value = "修改个人资料接口")
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateMemberUserRequest updateMemberUserRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberUserService.updateUser(userId,updateMemberUserRequest);
  }


  /**
   * 查找所有等级
   */
  @ApiOperation(nickname = "memberUserSearchAllGrade",value = "查找所有等级")
  @GetMapping("/searchAllGrade")
  public ResponseData<Page> searchAllGrade(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return memberUserService.searchAllGrade(page, size);
  }


}
