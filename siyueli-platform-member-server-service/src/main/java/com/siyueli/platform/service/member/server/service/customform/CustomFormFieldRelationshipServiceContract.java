package com.siyueli.platform.service.member.server.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomFormFieldRelationship;
import com.siyueli.platform.member.request.customform.CustomFormFieldRelationshipSearchRequest;
import com.siyueli.platform.member.request.customform.customformcategory.SearchCustomFormCategoryRequest;
import com.siyueli.platform.member.response.customform.CustomFormCategoryVo;
import com.siyueli.platform.member.response.customform.CustomFormFieldRelationshipVo;

/**
 * <p>
 * 表单与字段关系表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface CustomFormFieldRelationshipServiceContract extends IService<CustomFormFieldRelationship> {

    public ResponseData<PageResponse<CustomFormFieldRelationshipVo>> search(CustomFormFieldRelationshipSearchRequest requestParam);
}
