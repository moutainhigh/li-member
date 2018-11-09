package com.siyueli.platform.service.member.server.service.accountchecking;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.pojo.accountchecking.AccountChecking;
import com.siyueli.platform.service.member.server.mapper.accountchecking.AccountCheckingMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对账表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class AccountCheckingService extends ServiceImpl<AccountCheckingMapper, AccountChecking> implements AccountCheckingServiceContract {

}
