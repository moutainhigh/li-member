/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.callback.rights;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.rights.MemberRights;
import com.siyueli.platform.member.request.rights.addRights.SaveMemberRightsRequest;
import com.siyueli.platform.member.request.rights.updateRights.UpdateMemberRightsRequest;
import com.siyueli.platform.member.response.rights.common.MemberRightsResponse;
import com.siyueli.platform.service.member.client.service.rights.MemberRightsService;

import org.springframework.stereotype.Component;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 权益熔断器
 */
@Component
public class MemberRightsServiceFallBack implements MemberRightsService {

  @Override public ResponseData addRights(SaveMemberRightsRequest memberRights) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override public ResponseData updateRights(Long rightId, UpdateMemberRightsRequest memberRights) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override
  public ResponseData setStatus(Long id) {
    return ResponseData.build(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override
  public ResponseData<Page> searchAllRights(int page, int size) {
    return  new ResponseData<>(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }

  @Override public ResponseData<MemberRightsResponse>  searchSingleRights(Long id) {
    return  new ResponseData<>(ResponseBackCode.ERROR_DOWNGRADE.getValue(), ResponseBackCode.ERROR_DOWNGRADE.getMessage());
  }
}
