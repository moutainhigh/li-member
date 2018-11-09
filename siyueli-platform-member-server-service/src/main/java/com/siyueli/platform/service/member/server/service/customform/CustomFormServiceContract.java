package com.siyueli.platform.service.member.server.service.customform;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomForm;
import com.siyueli.platform.member.request.customform.CustomFormSearchRequest;
import com.siyueli.platform.member.response.customform.CustomFormVo;

/**
 * <p>
 * 表单 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface CustomFormServiceContract extends IService<CustomForm> {

    public ResponseData<PageResponse<CustomFormVo>> search(CustomFormSearchRequest requestParam);
}
