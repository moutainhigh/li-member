package com.siyueli.platform.service.member.server.service.recharge.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.recharge.RechargeOrder;
import com.siyueli.platform.service.member.server.mapper.recharge.RechargeOrderMapper;
import com.siyueli.platform.service.member.server.service.recharge.RechargeOrderService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class RechargeOrderServiceImpl extends ServiceImpl<RechargeOrderMapper, RechargeOrder> implements RechargeOrderService {

}
