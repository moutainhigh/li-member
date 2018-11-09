/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.member;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.member.backendUserLogin.BackendUserLoginRequest;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import com.siyueli.platform.member.request.member.updateAmount.UpdateBalanceRequest;
import com.siyueli.platform.member.request.member.updateUser.UpdateBackendMemberUserRequest;
import com.siyueli.platform.service.member.client.service.member.MemberBackendUserService;
import org.springframework.stereotype.Component;
import java.util.List;
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 会员后台熔断器
 */
@Component
public class MemberBackendUserServiceFallBack implements MemberBackendUserService {

  @Override public ResponseData updateUser(Long userId, UpdateBackendMemberUserRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData deleteAllUser(List<Long> userIds) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData deleteUser(Long id) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page> searchAllUser(int page, int size,SearchAllUserRequest request) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData searchUser(Long id) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData token() {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData userLogin(BackendUserLoginRequest backendUserLoginRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateBalance(Long userId, UpdateBalanceRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updatePoints(Long userId, UpdateBalanceRequest request) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page> searchBalance(Long userId, int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData<Page> searchPoints(Long userId, int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }


}
