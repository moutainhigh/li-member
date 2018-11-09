package com.siyueli.platform.member.common.activitywindow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ActWinGroupCommonVo {

    /**
     * 分组名
     */
    @ApiModelProperty("分组名")
    private String name;
    /**
     * 所属板块
     */
    @ApiModelProperty("所属板块")
    private Long plateId;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 分组标记
     */
    @ApiModelProperty("分组标记")
    private String groupFlag;

}
