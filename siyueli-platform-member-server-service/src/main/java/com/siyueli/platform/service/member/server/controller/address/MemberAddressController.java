package com.siyueli.platform.service.member.server.controller.address;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.pojo.address.MemberAddress;
import com.siyueli.platform.member.request.address.addAddress.SaveMemberAddressRequest;
import com.siyueli.platform.member.request.address.updateAddress.UpdateMemberAddressRequest;
import com.siyueli.platform.member.response.address.common.MemberAddressResponse;
import com.siyueli.platform.member.response.coupon.CouponTaskResponse;
import com.siyueli.platform.service.member.server.annotation.PermissionAnnotation;
import com.siyueli.platform.service.member.server.service.region.RegionServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberAddressServiceContract;
import com.siyueli.platform.service.member.server.util.JsonTokenUtil;
import org.springframework.beans.BeanUtils;
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
import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * 斯越里收货地址管理类
 */
@RequestMapping(path = "/member/address", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
public class MemberAddressController {

  private MemberAddressServiceContract memberAddressService;
  private RegionServiceContract regionService;
  private JsonTokenUtil jsonTokenUtil;

  @Autowired
  public MemberAddressController(
      MemberAddressServiceContract memberAddressService,
      RegionServiceContract regionService,JsonTokenUtil jsonTokenUtil) {
    this.memberAddressService = memberAddressService;
    this.regionService = regionService;
    this.jsonTokenUtil = jsonTokenUtil;
  }


  /*
   * 新增地址
   */
  @PostMapping("/addAddress")
  public ResponseData addAddress(@RequestBody SaveMemberAddressRequest request) {

    //设置full_address地址：省市区+详细地址
    String fullAddress =  regionService.getFullAddress(request.getProvinceCode(),request.getCityCode(),
                                                       request.getDistrictCode(),request.getAddress());

    //传入的省市区code无效
    if(fullAddress == null){
      return new ResponseData<>(ResponseBackCode.ERROR_CREATE_FAIL.getValue(), "传入的省市区code无效");
    }
    MemberAddress memberAddress = new MemberAddress();

    //保存地址
    BeanUtils.copyProperties(request,memberAddress);
    memberAddress.setFullAddress(fullAddress);
    memberAddressService.insert(memberAddress);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /*
   * 修改地址
   */
  @PutMapping("/updateAddress/{addressId}")
  public ResponseData updateAddress(@PathVariable Long addressId,
                                    @RequestBody UpdateMemberAddressRequest request) {

    MemberAddress storeAddress = memberAddressService.selectById(addressId);
    if(storeAddress == null){
      return ResponseData.build(ResponseBackCode.ERROR_PARAM_INVALID.getValue(),ResponseBackCode.ERROR_PARAM_INVALID.getMessage(),"地址id无效");
    }

    //设置full_address地址：省市区+详细地址
    String fullAddress =  regionService.getFullAddress(request.getProvinceCode(),request.getCityCode(),
                                                       request.getDistrictCode(),request.getAddress());

    //传入的省市区code无效
    if(fullAddress == null){
      return ResponseData.build(ResponseBackCode.ERROR_CREATE_FAIL.getValue(),ResponseBackCode.ERROR_CREATE_FAIL.getMessage(),"传入的省市区code无效");
    }
    MemberAddress memberAddress = new MemberAddress();
    BeanUtils.copyProperties(request,memberAddress);
    memberAddress.setId(addressId);
    memberAddress.setFullAddress(fullAddress);
    memberAddressService.updateById(memberAddress);

    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /*
   * 修改默认地址
   */
  @PutMapping("/setDefaultAddress/{addressId}")
  public ResponseData setDefaultAddress(@PathVariable Long addressId) {
    return  memberAddressService.setDefaultAddress(addressId);
  }


  /*
   * 删除等级
   */
  @PutMapping("/deleteAddress/{addressId}")
  public ResponseData deleteAddress(@PathVariable Long addressId) {
    MemberAddress memberAddress = memberAddressService.selectById(addressId);
    memberAddress.setStatus(1);
    memberAddressService.updateById(memberAddress);
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }

  /*
   * 查询单个地址
   */
  @GetMapping("/searchAddress/{addressId}")
  public ResponseData<MemberAddressResponse> searchAddress(@PathVariable Long addressId) {
    MemberAddress memberAddress = memberAddressService.selectById(addressId);
    MemberAddressResponse response = new MemberAddressResponse();
    BeanUtils.copyProperties(memberAddress,response);
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), response);
  }

  /**
   * 查询该用户所有的地址
   */
  @GetMapping("/searchAllAddress")
  public ResponseData<Page> searchAllAddress(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "15") int size) {
    Long userId = jsonTokenUtil.getCurrentUserId();
    Page userPage = new Page<MemberAddressResponse>(page, size);
    userPage.setAsc(false);
    userPage = memberAddressService.selectPage(userPage, new EntityWrapper<MemberAddress>().eq("user_id",userId).eq("status", 0));
    return new ResponseData<>(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage(), userPage);
  }

}
