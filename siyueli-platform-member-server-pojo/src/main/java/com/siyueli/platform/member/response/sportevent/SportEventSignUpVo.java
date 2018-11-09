package com.siyueli.platform.member.response.sportevent;

import com.siyueli.platform.member.common.sportevent.SportEventSignUpCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SportEventSignUpVo extends SportEventSignUpCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("更新时间")
    private Date updateAt;

    @ApiModelProperty("创建时间")
    private Date createAt;

    @ApiModelProperty("会员id")
    private Long memberId;

    @ApiModelProperty("已删除")
    private Integer isDeleted;

    @ApiModelProperty("状态")
    private Integer status;

}
