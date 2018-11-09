package com.siyueli.platform.member.request.accountchecking;

import com.siyueli.platform.member.common.accountchecking.AccountCheckingCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountCheckingUpdateRequest extends AccountCheckingCommonVo {

    @ApiModelProperty("主键")
    private Long id;
}
