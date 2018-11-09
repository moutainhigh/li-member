package com.siyueli.platform.member.request.activity.updateactivity;

import com.siyueli.platform.member.request.activity.common.CommonActRoleInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class UpdateActRoleInfoVo extends CommonActRoleInfoVo {

    private Long roleId;

    private List<UpdateActUserCouponVo> aucList;
}
