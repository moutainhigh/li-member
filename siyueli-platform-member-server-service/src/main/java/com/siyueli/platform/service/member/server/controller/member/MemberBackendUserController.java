/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.member;

import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.member.pojo.grade.MemberGrade;
import com.siyueli.platform.member.pojo.member.MemberAccountHistory;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateBackendMemberUserRequest;
import com.siyueli.platform.member.response.member.getuser.GetUserResponse;
import com.siyueli.platform.member.response.member.searchUser.MemberUserResponse;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.member.MemberAccountHistoryServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberGradeServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 会员基础服务类
 */
@RequestMapping(path = "/member/backend/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberBackendUserController {

  private MemberUserServiceContract memberUserService;

  private MemberGradeServiceContract memberGradeService;

  private MemberUserLoginTokenService memberUserLoginTokenService;

  private MemberAccountHistoryServiceContract memberAccountHistoryService;

  @Autowired
  public MemberBackendUserController(MemberUserServiceContract memberUserService,
                                 MemberGradeServiceContract memberGradeService,
                                 MemberUserLoginTokenService memberUserLoginTokenService,
                                 MemberAccountHistoryServiceContract memberAccountHistoryService){
    this.memberUserService = memberUserService;
    this.memberGradeService = memberGradeService;
    this.memberUserLoginTokenService = memberUserLoginTokenService;
    this.memberAccountHistoryService = memberAccountHistoryService;
  }


  /**
   * 修改单个会员
   */
  @PutMapping("/updateUser/{userId}")
  public ResponseData updateUser(@PathVariable Long userId, @RequestBody UpdateBackendMemberUserRequest request) {

    //根据传入的id查找数据库中是否有该用户
    MemberUser memberUser = memberUserService.selectById(userId);

    if(memberUser == null){
      return ResponseData.build(ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getValue(),ResponseBackCode.ERROR_OBJECT_NOT_EXIST.getMessage());
    }


    //保存用户至数据库之前，密码要加密,传过来的应该是密文：该密码是否已经修改
    String password = request.getPassword();
    if (password != null && !"".equals(password)) {
      request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
    } else {
      request.setPassword(memberUser.getPassword());
    }
    request.setId(userId);
    //更新时间
    request.setUpdateAt(LocalDateTime.now());
    memberUser = new MemberUser();
    BeanUtils.copyProperties(request, memberUser);
    memberUserService.updateById(memberUser);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 删除单个会员
   */
  @PutMapping("/deleteUser/{userId}")
  public ResponseData deleteUser(@PathVariable Long userId) {
    //查找是否含有该id的成员对象
    MemberUser user = memberUserService.selectById(userId);
    if (user == null) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }

    user.setStatus(1); //设置1为删除状态
    memberUserService.updateById(user);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /**
   * 删除多个会员
   */
  @PutMapping("/deleteAllUser")
  public ResponseData deleteAllUser(@RequestBody List<Long> userIds) {

    //查找相应的用户
    List<MemberUser> list = memberUserService.selectBatchIds(userIds);

    //设置所有用户的status为1
    for (MemberUser user : list) {
      user.setStatus(1);
    }
    memberUserService.updateBatchById(list);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage());

  }

  /**
   * 查找单个会员
   */
  @GetMapping("/searchUser/{userId}")
  public ResponseData<MemberUserResponse> searchUser(@PathVariable Long userId) {
    MemberUser user = memberUserService.selectById(userId);
    if(user.getGradeId() != null){
      MemberGrade memberGrade = memberGradeService.selectById(user.getGradeId());
      user.setGradeName(memberGrade.getName());
    }

    MemberUserResponse response = new MemberUserResponse();
    BeanUtils.copyProperties(user, response);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), response );
  }

  /**
   * 查找所有用户(分页查询)
   *
   * @param page 页码
   * @param size 每页显示的大小
   * @return 结果
   */
  @PostMapping("/searchAllUser")
  public ResponseData<Page> searchAllUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size,
      @RequestBody  SearchAllUserRequest request
  ) {
    return memberUserService.searchAllUser(page,size,request);
  }

  /**
   * 验证token
   */
  @PostMapping(value = "/token")
  public ResponseData token(HttpServletRequest request) {
    return memberUserLoginTokenService.checkToken(request.getHeader("token"));
  }

  /**
   * 后台登录
   */
  @PostMapping(value = "/userLogin")
  public ResponseData userLogin(@RequestBody BackendUserLoginRequest backendUserLoginRequest) {
    return memberUserService.backendLogin(backendUserLoginRequest);
  }

  /**
   * 修改余额
   */
  @PutMapping(value = "/updateBalance/{userId}")
  public ResponseData updateBalance(@PathVariable Long userId,
                                   @RequestBody UpdateBalanceRequest request) {
    return memberUserService.updateBalance(userId,request);
  }

  /**
   * 修改积分
   */
  @PutMapping(value = "/updatePoints/{userId}")
  public ResponseData updatePoints(@PathVariable Long userId,
                                   @RequestBody UpdateBalanceRequest request) {
    return memberUserService.updatePoints(userId,request);
  }

  /**
   * 查找余额记录
   */
  @GetMapping(value = "/searchBalance/{userId}")
  public ResponseData<Page> searchBalance(@PathVariable Long userId,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                          @RequestParam(value = "size", required = false, defaultValue = "15") int size){

    Page userPage = new Page<MemberAccountHistory>(page, size);
    userPage.setAsc(false);
    userPage = memberAccountHistoryService.selectPage(
        userPage, new EntityWrapper<MemberAccountHistory>().eq("user_id", userId).eq("type", MemberConstants.TYPE_BALANCE));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  /**
   * 查找余额记录
   */
  @GetMapping(value = "/searchPoints/{userId}")
  public ResponseData<Page> searchPoints(@PathVariable Long userId,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                          @RequestParam(value = "size", required = false, defaultValue = "15") int size){
    Page userPage = new Page<MemberAccountHistory>(page, size);
    userPage.setAsc(false);
    userPage = memberAccountHistoryService.selectPage(
        userPage, new EntityWrapper<MemberAccountHistory>().eq("user_id", userId).eq("type", MemberConstants.TYPE_POINTS));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

  @ApiOperation(nickname = "memberBackendSearchUser",value = "查找单个会员")
  @GetMapping("/getUserByOpenId")
  public ResponseData getUserByOpenId(@RequestParam("openId") String openId) {
    return memberUserService.getUserByOpenId(openId);
  }

  @ApiOperation(nickname = "getUserById",value = "查找单个会员")
  @GetMapping("/getUserById")
  public ResponseData getUserById(@RequestParam("id") Long id) {
    MemberUser memberUser = memberUserService.selectById(id);
    GetUserResponse respData = null;
    if (memberUser != null) {
      respData = new GetUserResponse();
      BeanUtils.copyProperties(memberUser, respData);
    }
    return ResponseUtil.success(respData);

  }

}
