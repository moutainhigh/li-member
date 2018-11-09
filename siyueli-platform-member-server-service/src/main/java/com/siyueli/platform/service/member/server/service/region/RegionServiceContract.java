package com.siyueli.platform.service.member.server.service.region;

import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.pojo.region.Region;
import com.siyueli.platform.member.request.address.addAddress.SaveMemberAddressRequest;

import java.util.List;

/**
 * <p>
 * 行政区 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface RegionServiceContract extends IService<Region> {

  /**
   * 构造树型结构的地区信息
   * @param nodes 所有地区信息
   */
  List<Region> build(List<Region> nodes);

  /**
   * 查找指定地区id的信息
   */
  List<Region> selectByRegionId(List<Long> regionIds);

  /**
   * 获取：省市区+详细地址
   */
  String getFullAddress(Long provinceCode,Long cityCode,Long districtCode,String address);

}
