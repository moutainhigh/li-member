package com.siyueli.platform.service.member.server.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomFormCategory;
import com.siyueli.platform.member.request.customform.customformcategory.SearchCustomFormCategoryRequest;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.member.response.customform.CustomFormCategoryVo;

/**
 * <p>
 * 表单分类表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface CustomFormCategoryServiceContract extends IService<CustomFormCategory> {

    public ResponseData<PageResponse<CustomFormCategoryVo>> search(SearchCustomFormCategoryRequest requestParam);
}
