/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.service.member.client.controller.address;

import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.request.address.addAddress.SaveMemberAddressRequest;
import com.siyueli.platform.member.request.address.updateAddress.UpdateMemberAddressRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.service.member.client.service.address.MemberAddressService;
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

@Api(tags = "斯越里_个人地址管理接口")
@RequestMapping(path = "/member/address", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberAddressController {


  private MemberAddressService memberAddressService;

  @Autowired
  public MemberAddressController(MemberAddressService memberAddressService){
    this.memberAddressService = memberAddressService;
  }


  /*
   * 新增地址
   */
  @ApiOperation(nickname = "memberAddressAddAddress",value = "新增地址接口")
  @PostMapping("/addAddress")
  public ResponseData addAddress(@RequestBody @Valid SaveMemberAddressRequest saveMemberAddressRequest, BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberAddressService.addAddress(saveMemberAddressRequest);
  }

  /*
   * 修改地址
   */
  @ApiOperation(nickname = "memberAddressUpdateAddress",value = "修改地址接口")
  @PutMapping("/updateAddress/{addressId}")
  public ResponseData updateAddress(@PathVariable Long addressId,
                                    @RequestBody @Valid UpdateMemberAddressRequest updateMemberAddressRequest,
                                    BindingResult result) {
    //请求的数据参数格式不正确
    if (result.hasErrors()) {
      return ResponseData.build(
          ResponseBackCode.ERROR_PARAM_INVALID.getValue(),
          ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),result.getAllErrors()
      );
    }
    return memberAddressService.updateAddress(addressId,updateMemberAddressRequest);
  }

  /*
   * 设为默认地址
   */
  @ApiOperation(nickname = "memberAddressEditDefaultAddress",value = "设置默认地址接口")
  @PutMapping("/setDefaultAddress/{addressId}")
  public ResponseData setDefaultAddress(@PathVariable Long  addressId) {
    return memberAddressService.setDefaultAddress(addressId);
  }


  /*
   * 删除地址
   */
  @ApiOperation(nickname = "memberAddressDeleteAddress",value = "删除地址接口")
  @PutMapping("/deleteAddress/{addressId}")
  public ResponseData deleteAddress(@PathVariable Long  addressId) {
    return memberAddressService.deleteAddress(addressId);
  }

  /*
   * 查询单个地址
   */
  @ApiOperation(nickname = "memberAddressSearchAddress",value = "查询单个地址接口")
  @GetMapping("/searchAddress/{addressId}")
  public ResponseData<MemberAddressResponse> searchAddress(@PathVariable Long  addressId) {
    return memberAddressService.searchAddress(addressId);
  }

  /**
   * 查询该用户所有的地址
   */
  @ApiOperation(nickname = "memberAddressSearchALLAddress",value = "查询该用户所有的地址接口")
  @GetMapping("/searchAllAddress")
  public ResponseData<Page> searchAllAddress(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    return memberAddressService.searchAllAddress(page, size);
  }

}
