package com.siyueli.platform.service.member.server.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomFieldOption;
import com.siyueli.platform.member.request.customform.customfieldoption.SearchCustomFieldOptionRequest;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;

/**
 * <p>
 * 字段选项表 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface CustomFieldOptionServiceContract extends IService<CustomFieldOption> {

    public ResponseData<PageResponse<CustomFieldOptionVo>> search(SearchCustomFieldOptionRequest requestParam);
}
