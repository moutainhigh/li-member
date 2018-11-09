package com.siyueli.platform.member.common.customform;

import lombok.Data;

@Data
public class CustomFormCategoryCommonVo {

    /**
     * 名称
     */
    private String name;
    /**
     * 父级id
     */
    private Long parentId;
}
