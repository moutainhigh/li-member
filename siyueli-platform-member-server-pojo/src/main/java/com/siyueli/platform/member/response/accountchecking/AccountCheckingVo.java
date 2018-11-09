package com.siyueli.platform.member.response.accountchecking;

import com.siyueli.platform.member.common.accountchecking.AccountCheckingCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AccountCheckingVo extends AccountCheckingCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("创建时间")
    private Date createAt;

    @ApiModelProperty("更新时间")
    private Date updateAt;

}
