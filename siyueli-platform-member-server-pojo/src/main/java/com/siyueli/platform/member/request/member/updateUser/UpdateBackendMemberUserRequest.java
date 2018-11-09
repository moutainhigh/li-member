/*
 * (C) Copyright 2018 Siyue Holding Group.
 */
package com.siyueli.platform.member.request.member.updateUser;

import com.siyueli.platform.member.common.member.MemberUserVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 后台修改个人资料请求对象
 */
@Data
@ApiModel(value = "UpdateBackendMemberUserRequest")
public class UpdateBackendMemberUserRequest extends MemberUserVo {
}
