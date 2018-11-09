package com.siyueli.platform.member.common.customform;

import lombok.Data;

@Data
public class CustomFieldOptionCommonVo {

    /**
     * 字段id
     */
    private Long fieldId;
    /**
     * 字段选项值
     */
    private String fieldValue;
    /**
     * 展示类型
     */
    private Integer showType;
}
