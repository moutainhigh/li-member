/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.service.address;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.address.addAddress.SaveMemberAddressRequest;
import com.siyueli.platform.member.request.address.updateAddress.UpdateMemberAddressRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.service.member.client.callback.address.MemberAddressServiceFallBack;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.siyue.platform.base.ResponseData;

/**
 * 调用会员管理服务生产者的接口
 */
@FeignClient(name = "siyueli-member-service", path = "/member/address", fallback = MemberAddressServiceFallBack.class)
public interface MemberAddressService {

  @RequestMapping(value = "/addAddress", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData addAddress(SaveMemberAddressRequest saveMemberAddressRequest);

  @RequestMapping(value = "/updateAddress/{addressId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData updateAddress(@PathVariable(value = "addressId") Long addressId,UpdateMemberAddressRequest updateMemberAddressRequest);

  @RequestMapping(value = "/setDefaultAddress/{addressId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData setDefaultAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/deleteAddress/{addressId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData deleteAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/searchAddress/{addressId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<MemberAddressResponse> searchAddress(@PathVariable(value = "addressId") Long addressId);

  @RequestMapping(value = "/searchAllAddress", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseData<Page> searchAllAddress(@RequestParam("page") int page, @RequestParam("size") int size);


}
