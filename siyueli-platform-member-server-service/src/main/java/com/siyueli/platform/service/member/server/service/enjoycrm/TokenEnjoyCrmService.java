package com.siyueli.platform.service.member.server.service.enjoycrm;

import com.siyueli.platform.member.vo.enjoycrm.gettoken.GetTokenResponse;
import com.siyueli.platform.service.member.server.config.EnjoyCrmConfig;

public interface TokenEnjoyCrmService {

    GetTokenResponse getToken(boolean expiredFlag);

}
