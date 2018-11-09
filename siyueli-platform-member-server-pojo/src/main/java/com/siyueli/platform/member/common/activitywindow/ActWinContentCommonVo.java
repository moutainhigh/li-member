package com.siyueli.platform.member.common.activitywindow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ActWinContentCommonVo {

    /**
     * 内容名
     */
    @ApiModelProperty("内容名")
    private String name;
    /**
     * 分组id
     */
    @ApiModelProperty("分组id")
    private Long groupId;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
    private String picUrl;
    /**
     * 内容url
     */
    @ApiModelProperty("内容url")
    private String contentUrl;
    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

}
