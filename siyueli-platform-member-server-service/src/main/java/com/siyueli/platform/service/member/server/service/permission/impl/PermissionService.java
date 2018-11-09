package com.siyueli.platform.service.member.server.service.permission.impl;

import com.siyueli.platform.member.pojo.PermissionAction;
import com.siyueli.platform.member.pojo.PermissionRole;
import com.siyueli.platform.member.pojo.UserRole;
import com.siyueli.platform.member.response.PermissionActionVo;
import com.siyueli.platform.service.member.server.service.permission.PermissionActionServiceContract;
import com.siyueli.platform.service.member.server.service.permission.PermissionRoleServiceContract;
import com.siyueli.platform.service.member.server.service.permission.PermissionServiceContract;
import com.siyueli.platform.service.member.server.service.permission.UserRoleServiceContract;
import com.siyueli.platform.service.member.server.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService implements PermissionServiceContract {

    @Autowired
    private UserRoleServiceContract userRoleService;

    @Autowired
    private PermissionRoleServiceContract permissionRoleServiceContract;

    @Autowired
    private PermissionActionServiceContract permissionActionServiceContract;

    @Override
    public List<PermissionActionVo> getPermissionListByUserId(Long userId) {
        List<UserRole> userRoleList = userRoleService.getByUserId(userId);
        if (userRoleList != null && userRoleList.size() > 0) {
            List<Long> roleIdList = new ArrayList<Long>();
            for (UserRole ur : userRoleList) {
                roleIdList.add(ur.getRoleId());
            }
            List<PermissionRole> permissionRoleList = permissionRoleServiceContract.getByRoleIds(roleIdList);
            if (permissionRoleList != null && permissionRoleList.size() > 0) {
                List<Long> permissionIdList = new ArrayList<Long>();

                for (PermissionRole pr : permissionRoleList) {
                    permissionIdList.add(pr.getPermissionId());
                }

                List<PermissionAction> permissionActionList = permissionActionServiceContract.selectBatchIds(permissionIdList);
                List<PermissionActionVo> voList = null;
                try {
                    voList = ConvertUtil.convertList(PermissionActionVo.class, permissionActionList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return voList;

            }

        }

        return null;
    }
}
