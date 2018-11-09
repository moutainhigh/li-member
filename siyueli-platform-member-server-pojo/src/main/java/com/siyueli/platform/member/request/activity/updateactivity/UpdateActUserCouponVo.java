package com.siyueli.platform.member.request.activity.updateactivity;

import com.siyueli.platform.member.request.activity.addactivity.AddActUserCouponVo;
import com.siyueli.platform.member.request.activity.common.CommonActUserCouponVo;
import lombok.Data;

@Data
public class UpdateActUserCouponVo extends CommonActUserCouponVo {

    private Long aucId;
}
