package com.siyueli.platform.service.member.server.service.customform.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.coupon.CouponTask;
import com.siyueli.platform.member.pojo.customform.CustomFieldOption;
import com.siyueli.platform.member.request.customform.customfieldoption.SearchCustomFieldOptionRequest;
import com.siyueli.platform.member.response.customform.CustomFieldOptionVo;
import com.siyueli.platform.service.member.server.controller.customform.CustomFieldOptionController;
import com.siyueli.platform.service.member.server.mapper.customform.CustomFieldOptionMapper;
import com.siyueli.platform.service.member.server.service.customform.CustomFieldOptionServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字段选项表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class CustomFieldOptionService extends ServiceImpl<CustomFieldOptionMapper, CustomFieldOption> implements CustomFieldOptionServiceContract {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomFieldOptionService.class);

    @Override
    public ResponseData<PageResponse<CustomFieldOptionVo>> search(SearchCustomFieldOptionRequest requestParam) {
        EntityWrapper<CustomFieldOption> entityWrapper = new EntityWrapper<CustomFieldOption>();
        Page<CustomFieldOption> page = new Page<CustomFieldOption>(requestParam.getPage(), requestParam.getSize());
        Page<CustomFieldOption> resultPage = selectPage(page, entityWrapper);
        try {
            PageResponse<CustomFieldOptionVo> pageResponse = ConvertUtil.getPageResponse(resultPage, CustomFieldOptionVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }
}
