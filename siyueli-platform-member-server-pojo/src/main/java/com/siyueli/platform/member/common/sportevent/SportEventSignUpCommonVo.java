package com.siyueli.platform.member.common.sportevent;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SportEventSignUpCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("赛事表单id")
    private Long eventFormId;

    @ApiModelProperty("赛事名称")
    private String sportName;

    @ApiModelProperty("报名开始时间")
    private Date beginTime;

    @ApiModelProperty("报名结束时间")
    private Date endTime;

    @ApiModelProperty("规则说明")
    private String ruleDesc;

    @ApiModelProperty("报名费用")
    private BigDecimal fee;

    @ApiModelProperty("需要审核")
    private Integer needAudit;

    @ApiModelProperty("需要运动员认证")
    private Integer needVerify;

    @ApiModelProperty("仅限成人运动员")
    private Integer onlyAdultAthlete;



}
