package com.siyueli.platform.service.member.server.service.member;

import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.address.MemberAddress;

import cn.siyue.platform.base.ResponseData;

/**
 * <p>
 * 会员地址表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface MemberAddressServiceContract extends IService<MemberAddress> {

  ResponseData setDefaultAddress(Long addressId);

}
