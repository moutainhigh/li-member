package com.siyueli.platform.member.request.activity.addactivity;

import com.siyueli.platform.member.request.activity.common.CommonActRoleInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class AddActRoleInfoVo extends CommonActRoleInfoVo {

    private List<AddActUserCouponVo> aucList;
}
