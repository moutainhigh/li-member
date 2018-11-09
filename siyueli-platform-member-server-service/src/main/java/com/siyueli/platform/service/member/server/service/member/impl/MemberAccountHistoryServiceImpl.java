package com.siyueli.platform.service.member.server.service.member.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.member.MemberAccountHistory;
import com.siyueli.platform.service.member.server.mapper.member.MemberAccountHistoryMapper;
import com.siyueli.platform.service.member.server.service.member.MemberAccountHistoryServiceContract;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * 帐户历史表 服务实现类
 */
@Primary
@Service
public class MemberAccountHistoryServiceImpl extends ServiceImpl<MemberAccountHistoryMapper, MemberAccountHistory> implements MemberAccountHistoryServiceContract {

}
