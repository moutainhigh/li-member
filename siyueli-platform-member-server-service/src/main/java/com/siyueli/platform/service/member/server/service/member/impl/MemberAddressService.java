package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.address.MemberAddress;
import com.siyueli.platform.service.member.server.mapper.member.MemberAddressMapper;
import com.siyueli.platform.service.member.server.service.member.MemberAddressServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;

/**
 * <p>
 * 会员地址表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class MemberAddressService extends ServiceImpl<MemberAddressMapper, MemberAddress> implements MemberAddressServiceContract {

  /**
   * 设置默认地址
   */
  @Transactional(rollbackFor = Exception.class)
  @Override
  public ResponseData setDefaultAddress(Long addressId) {
    //获取用户id
    MemberAddress memberAddress = selectById(addressId);
    Long userId = memberAddress.getUserId();
    Map map = new HashMap();
    map.put("user_id",userId);
    List<MemberAddress> list = selectByMap(map);
    if(list != null && list.size() > 0){
      int len = list.size();
      for(int i = 0; i < len;i++){
        MemberAddress storageAddress = list.get(i);
        //设置默认地址为1
        if(storageAddress.getId().equals(memberAddress.getId())){
          storageAddress.setDefaultAddress(1);
        }else{
          storageAddress.setDefaultAddress(0);
        }
        list.set(i,storageAddress);
      }
      updateBatchById(list);
    }
    return ResponseData.build(ResponseBackCode.SUCCESS.getValue(),ResponseBackCode.SUCCESS.getMessage());
  }
}
