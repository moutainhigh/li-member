package com.siyueli.platform.service.member.server.mapper.region;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.siyueli.platform.member.pojo.region.Region;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 行政区 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface RegionMapper extends BaseMapper<Region> {

  List<Region> selectByRegionId(@Param("regionIds") List<Long> regionIds);
}
