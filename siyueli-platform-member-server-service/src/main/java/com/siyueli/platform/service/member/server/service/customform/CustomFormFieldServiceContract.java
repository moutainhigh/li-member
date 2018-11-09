package com.siyueli.platform.service.member.server.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomFormField;
import com.siyueli.platform.member.request.customform.customformfield.SearchCustomFormFieldRequest;
import com.siyueli.platform.member.response.customform.CustomFormFieldVo;

/**
 * <p>
 * 表单字段表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface CustomFormFieldServiceContract extends IService<CustomFormField> {

    public ResponseData<PageResponse<CustomFormFieldVo>> search(SearchCustomFormFieldRequest requestParam);
}
