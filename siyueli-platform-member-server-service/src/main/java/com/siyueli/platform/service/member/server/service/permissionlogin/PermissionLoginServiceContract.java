package com.siyueli.platform.service.member.server.service.permissionlogin;

import cn.siyue.platform.base.ResponseData;
import com.baomidou.mybatisplus.service.IService;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.pojo.permissionlogin.PermissionLogin;
import com.siyueli.platform.member.request.permissionlogin.PermissionLoginSearchRequest;
import com.siyueli.platform.member.response.permissionlogin.PermissionLoginVo;

/**
 * <p>
 * 访问的uri是否需要登录 服务类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
public interface PermissionLoginServiceContract extends IService<PermissionLogin> {

    public ResponseData<PageResponse<PermissionLoginVo>> search(PermissionLoginSearchRequest requestParam);

    public PermissionLoginVo getByUri(String uri);
}
