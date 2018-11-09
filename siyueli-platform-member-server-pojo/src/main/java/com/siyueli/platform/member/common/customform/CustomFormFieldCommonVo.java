package com.siyueli.platform.member.common.customform;

import lombok.Data;

@Data
public class CustomFormFieldCommonVo {

    /**
     * 名称
     */
    private String name;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 校验规则
     */
    private String validateRegular;
}
