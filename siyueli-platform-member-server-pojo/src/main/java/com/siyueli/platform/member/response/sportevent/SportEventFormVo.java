package com.siyueli.platform.member.response.sportevent;

import com.siyueli.platform.member.common.sportevent.SportEventFormCommonVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SportEventFormVo extends SportEventFormCommonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("更新时间")
    private Date updateAt;

    @ApiModelProperty("创建时间")
    private Date createAt;

}
