package com.siyueli.platform.member.request.customform.customformcategory;

import com.siyueli.platform.member.common.customform.CustomFormCategoryCommonVo;
import lombok.Data;

@Data
public class UpdateCustomFormCategoryRequest extends CustomFormCategoryCommonVo {
    private Long id;
}
