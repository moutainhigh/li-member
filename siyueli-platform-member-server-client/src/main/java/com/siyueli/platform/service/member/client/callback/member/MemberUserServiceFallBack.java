/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.request.member.registerUser.RegisterUserRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateMemberUserRequest;
import com.siyueli.platform.member.request.member.userLogin.UserLoginRequest;
import com.siyueli.platform.member.response.member.userLogin.UserLoginResponse;
import com.siyueli.platform.service.member.client.service.member.MemberUserService;

import org.springframework.stereotype.Component;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 会员前台熔断器
 */
@Component
public class MemberUserServiceFallBack implements MemberUserService {

  @Override public ResponseData registerUser(RegisterUserRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData userLogin(UserLoginRequest request) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override
  public ResponseData userLogout() {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData getUserInfo() {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateUser(Long userId, UpdateMemberUserRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page> searchAllGrade(int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
