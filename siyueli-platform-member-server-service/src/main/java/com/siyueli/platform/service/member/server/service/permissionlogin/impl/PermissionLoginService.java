package com.siyueli.platform.service.member.server.service.permissionlogin.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.permissionlogin.PermissionLogin;
import com.siyueli.platform.member.request.permissionlogin.PermissionLoginSearchRequest;
import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;
import com.siyueli.platform.service.member.server.mapper.permissionlogin.PermissionLoginMapper;
import com.siyueli.platform.service.member.server.service.permissionlogin.PermissionLoginServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 访问的uri是否需要登录 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PermissionLoginService extends ServiceImpl<PermissionLoginMapper, PermissionLogin> implements PermissionLoginServiceContract {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionLoginService.class);

    @Override
    public ResponseData<PageResponse<PermissionLoginVo>> search(PermissionLoginSearchRequest requestParam) {
        EntityWrapper<PermissionLogin> entityWrapper = new EntityWrapper<PermissionLogin>();
        Page<PermissionLogin> page = new Page<PermissionLogin>(requestParam.getPage(), requestParam.getSize());
        Page<PermissionLogin> resultPage = selectPage(page, entityWrapper);
        try {
            PageResponse<PermissionLoginVo> pageResponse = ConvertUtil.getPageResponse(resultPage, PermissionLoginVo.class);
            return ResponseUtil.success(pageResponse);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ResponseUtil.fail();
    }

    @Override
    public PermissionLoginVo getByUri(String uri) {
        EntityWrapper<PermissionLogin> entityWrapper = new EntityWrapper<PermissionLogin>();
        entityWrapper.eq("uri", uri);
        PermissionLogin entity = selectOne(entityWrapper);
        if (entity != null) {
            PermissionLoginVo vo = new PermissionLoginVo();
            BeanUtils.copyProperties(entity, vo);
            return vo;
        }
        return null;
    }
}
