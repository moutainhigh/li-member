package com.siyueli.platform.service.member.server.service.customform.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.customform.CustomForm;
import com.siyueli.platform.member.pojo.customform.CustomFormField;
import com.siyueli.platform.member.request.customform.CustomFormSearchRequest;
import com.siyueli.platform.member.response.customform.CustomFormFieldVo;
import com.siyueli.platform.member.response.customform.CustomFormVo;
import com.siyueli.platform.service.member.server.mapper.customform.CustomFormMapper;
import com.siyueli.platform.service.member.server.service.customform.CustomFormServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 表单 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class CustomFormService extends ServiceImpl<CustomFormMapper, CustomForm> implements CustomFormServiceContract {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFormService.class);

    @Override
    public ResponseData<PageResponse<CustomFormVo>> search(CustomFormSearchRequest requestParam) {
        EntityWrapper<CustomForm> entityWrapper = new EntityWrapper<CustomForm>();
        Page<CustomForm> page = new Page<CustomForm>(requestParam.getPage(), requestParam.getSize());
        Page<CustomForm> resultPage = selectPage(page, entityWrapper);
        try {
            PageResponse<CustomFormVo> pageResponse = ConvertUtil.getPageResponse(resultPage, CustomFormVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }
}
