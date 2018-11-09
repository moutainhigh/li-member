/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.member.registerUser.RegisterUserRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import com.siyueli.platform.member.request.member.userLogin.UserLoginRequest;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.member.MemberGradeServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
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
import javax.servlet.http.HttpServletRequest;
import cn.siyue.platform.base.ResponseData;

/**
 * 会员基础服务类
 */
@RequestMapping(path = "/member/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberUserController {

  private MemberUserServiceContract memberUserService;
  private MemberGradeServiceContract memberGradeService;

  @Autowired
  public MemberUserController(MemberUserServiceContract memberUserService,
                              MemberGradeServiceContract memberGradeService){
    this.memberUserService = memberUserService;
    this.memberGradeService = memberGradeService;
  }


  /**
   * 手机号注册
   */
  @PostMapping("/registerUser")
  public ResponseData registerUser(@RequestBody RegisterUserRequest request) {
    return memberUserService.registerUser(request.getCellphone(),request.getOpenId(),request.getVerificateCode());
  }


  /**
   * 前台登录
   */
  @PostMapping(value = "/userLogin")
  public ResponseData userLogin(@RequestBody UserLoginRequest request) {
    return memberUserService.userLogin(request.getJsCode());
  }


  /**
   * 登出
   */
  @PostMapping(value = "/userLogout")
  public ResponseData userLogout(HttpServletRequest request) {
    return memberUserService.userLogout(request.getHeader("token"));
  }

  /**
   * 拉取用户信息接口
   */
  @GetMapping(value = "/getUserInfo")
  public ResponseData getUserInfo() {
    return memberUserService.getUserInfo();
  }

  @GetMapping("/searchAllGrade")
  public ResponseData<Page> searchAllGrade(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    return memberGradeService.searchAllGrade(page,size);
  }



  /**
   * 修改个人资料
   */
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId, @RequestBody UpdateMemberUserRequest request) {
    return  memberUserService.updateUser(userId,request);
  }

}
