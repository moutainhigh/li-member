package com.siyueli.platform.service.member.server.mapper.member;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.siyueli.platform.member.dto.memberuser.BalanceInfoDto;
import com.siyueli.platform.member.pojo.member.MemberUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.siyueli.platform.member.request.member.searchAllUser.SearchAllUserRequest;
import java.util.List;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Repository
public interface MemberUserMapper extends BaseMapper<MemberUser> {


  void addBalanceAndPoints(@Param("id") Long id, @Param("balanceInfo") BalanceInfoDto balanceInfo);

  List<MemberUser> searchAllUser(@Param("limit") Integer limit, @Param("offset") Integer offset,@Param("registerTimeBefore") String registerTimeBefore,@Param("registerTimeAfter")
      String registerTimeAfter, @Param("request") SearchAllUserRequest request);


}
