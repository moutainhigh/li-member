package com.siyueli.platform.member.common.activitywindow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ActWinPlateCommonVo {

    /**
     * 板块名
     */
    @ApiModelProperty("板块名")
    private String name;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 板块标记
     */
    @ApiModelProperty("板块标记")
    private String plateFlag;
}
