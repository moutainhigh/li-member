package com.siyueli.platform.service.member.server.service.member.impl;

import com.siyueli.platform.member.dto.memberuser.BalanceInfoDto;
import com.siyueli.platform.service.member.server.mapper.member.MemberUserMapper;
import com.siyueli.platform.service.member.server.service.member.MemberUserTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gavin
 * 会员服务
 */
@Service
public class MemberUserTwoServiceImpl implements MemberUserTwoService {

    @Autowired
    private MemberUserMapper memberUserMapper;

    @Override
    public void addBalanceAndPoints(Long id, BalanceInfoDto balanceInfo) {
        memberUserMapper.addBalanceAndPoints(id, balanceInfo);
    }
}
