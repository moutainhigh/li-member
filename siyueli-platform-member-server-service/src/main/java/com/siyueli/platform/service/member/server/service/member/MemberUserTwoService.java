package com.siyueli.platform.service.member.server.service.member;

import com.siyueli.platform.member.dto.memberuser.BalanceInfoDto;

public interface MemberUserTwoService {

    void addBalanceAndPoints(Long id, BalanceInfoDto balanceInfo);
}
