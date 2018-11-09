/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.server.controller.member;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.service.member.server.annotation.LoggerAspectAnnotation;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.member.MemberUserLoginTokenService;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.util.AESUtils;

/**
 * 会员基础服务类
 */
@RestController
public class MemberBusinessController {

  private MemberUserServiceContract memberUserService;
  private MemberUserLoginTokenService memberUserLoginTokenService;

  @Autowired
  public MemberBusinessController(MemberUserServiceContract memberUserService,
                                     MemberUserLoginTokenService memberUserLoginTokenService){
    this.memberUserService = memberUserService;
    this.memberUserLoginTokenService = memberUserLoginTokenService;
  }

  /*
   * 新建用户
   */
  @LoggerAspectAnnotation
  @PostMapping("/business/addUser")
  public ResponseData addUser(@RequestBody MemberUser memberUser) {

    try {
      //保存用户至数据库之前，密码要加密
      memberUser.setPassword(AESUtils.encrypt(memberUser.getPassword()));

      memberUserService.insert(memberUser);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), memberUser
      );
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseData.build(
          ResponseBackCode.ERROR_CREATE_FAIL.getValue(),
          ResponseBackCode.ERROR_CREATE_FAIL.getMessage()
      );
    }
  }

  /**
   * 修改单个用户
   */
  @LoggerAspectAnnotation
  @PostMapping("/business/updateUser")
  public ResponseData updateUser(@RequestBody MemberUser memberUser) {
    try {

      if (memberUser.getId() == null || "".equals(memberUser.getId())) {
        return ResponseData.build(
            ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
            ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
        );
      }

      //根据传入的id查找数据库中是否有该用户
      MemberUser user = memberUserService.selectById(memberUser.getId());

      //保存用户至数据库之前，密码要加密,传过来的应该是密文：该密码是否已经修改？
      memberUser.setPassword(AESUtils.encrypt(memberUser.getPassword()));


      memberUser.setCreateAt(user.getCreateAt());

      memberUserService.updateById(memberUser);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), memberUser
      );
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_UPDATE_FAIL.getValue(),
          ResponseBackCode.ERROR_UPDATE_FAIL.getMessage()
      );
    }
  }

  /**
   * 删除单个用户
   */
  @LoggerAspectAnnotation
  @GetMapping("/business/deleteUser")
  public ResponseData deleteUser(@RequestParam("id") Long id) {

    //判断id是否合法
    if (id < 0) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    try {
      MemberUser user = memberUserService.selectById(id);
      user.setStatus(1); //设置1为删除状态
      memberUserService.updateById(user);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), id
      );
    } catch (Exception e) {
      return ResponseData.build(ResponseBackCode.ERROR_DELETE_FAIL.getValue(),
                                ResponseBackCode.ERROR_DELETE_FAIL.getMessage(), id
      );
    }
  }

  /**
   * 删除多个用户
   */
  @LoggerAspectAnnotation
  @GetMapping("/business/deleteAllUser")
  public ResponseData deleteAllUser(@RequestParam("id") String id) {
    //请求的数据参数格式不正确
    if (id != null && !"".equals(id)) {
      try {
        String[] ids = id.split(",");
        List<Long> idList = new ArrayList<>();
        for (String strId : ids) {
          Long userId = Long.parseLong(strId);
          idList.add(userId);
        }
        //查找相应的用户
        List<MemberUser> list = memberUserService.selectBatchIds(idList);
        //设置所有用户的status为1
        for (MemberUser user : list) {
          user.setStatus(1);
        }
        memberUserService.updateBatchById(list);
        return ResponseData.build(ResponseBackCode.SUCCESS.getValue(), ResponseBackCode.SUCCESS.getMessage(), id);
      } catch (Exception e) {
        return ResponseData.build(ResponseBackCode.ERROR_DELETE_FAIL.getValue(),
                                  ResponseBackCode.ERROR_DELETE_FAIL.getMessage(), id
        );
      }
    }
    return ResponseData.build(ResponseBackCode.ERROR_DELETE_FAIL.getValue(),
                              ResponseBackCode.ERROR_DELETE_FAIL.getMessage(), id
    );
  }

  /**
   * 查找单个用户
   */
  @LoggerAspectAnnotation
  @GetMapping("/business/searchUser")
  public ResponseData searchUser(@RequestParam("id") Long id) {
    //判断id是否合法
    if (id < 0) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
      );
    }
    try {
      MemberUser user = memberUserService.selectById(id);
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), user
      );
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_NOT_FOUND.getValue(),
          ResponseBackCode.ERROR_NOT_FOUND.getMessage()
      );
    }
  }

  /**
   * 查找所有用户(分页查询)
   *
   * @param page 页码
   * @param size 每页显示的大小
   * @return 结果
   */
  @LoggerAspectAnnotation
  @GetMapping("/business/searchAllUser")
  public ResponseData searchAllUser(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size
  ) {
    try {
      Page userPage = new Page<MemberUser>(page, size);
      userPage.setAsc(false);
      List<MemberUser> list = memberUserService.selectPage(
          userPage, new EntityWrapper<MemberUser>().eq("status", 0)).getRecords();
      return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),
                                ResponseBackCode.SUCCESS.getMessage(), list
      );
    } catch (Exception e) {
      return ResponseData.build(
          ResponseBackCode.ERROR_NOT_FOUND.getValue(),
          ResponseBackCode.ERROR_NOT_FOUND.getMessage()
      );
    }
  }

  /**
   * 验证token
   */
  @LoggerAspectAnnotation
  @GetMapping(value = "/business/token")
  public ResponseData token(@RequestParam("token") String token) {
    try {
      if (token == null || "".equals(token)) {
        return ResponseData.build(
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
            ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
        );
      }
      return memberUserLoginTokenService.checkToken(token);
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseData.build(
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getValue(),
          ResponseBackCode.ERROR_TOKEN_TIMEOUT_CODE.getMessage()
      );
    }
  }

  /**
   * 登录
   */
  @LoggerAspectAnnotation
  @PostMapping(value = "/business/userLogin")
  public ResponseData userLogin(
      @RequestParam("userCode") String userCode,
      @RequestParam("password") String password
  ) {
    try {
      if (userCode == null || "".equals(userCode)
          || password == null || "".equals(password)) {
        return ResponseData.build(
            ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
            ResponseBackCode.ERROR_PARAM_INVALID.getMessage()
        );
      }
      return null;//memberUserLoginTokenService.userLogin(userCode,password,"");
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseData.build(
          ResponseBackCode.ERROR_FAIL.getValue(),
          ResponseBackCode.ERROR_FAIL.getMessage()
      );
    }
  }
}
