/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.address;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.address.addAddress.SaveMemberAddressRequest;
import com.siyueli.platform.member.request.address.updateAddress.UpdateMemberAddressRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.service.member.client.service.address.MemberAddressService;

import org.springframework.stereotype.Component;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 个人地址管理熔断器
 */
@Component
public class MemberAddressServiceFallBack implements MemberAddressService {

  @Override
  public ResponseData addAddress(SaveMemberAddressRequest saveMemberAddressRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData updateAddress(Long addressId, UpdateMemberAddressRequest updateMemberAddressRequest) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override public ResponseData setDefaultAddress(Long addressId) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }


  @Override
  public ResponseData deleteAddress(Long id) {
    return ResponseData.build(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override
  public ResponseData<MemberAddressResponse> searchAddress(Long id) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }

  @Override
  public ResponseData<Page> searchAllAddress(int page, int size) {
    return new ResponseData<>(
        ResponseBackCode.ERROR_DOWNGRADE.getValue(),
        ResponseBackCode.ERROR_DOWNGRADE.getMessage()
    );
  }
}
