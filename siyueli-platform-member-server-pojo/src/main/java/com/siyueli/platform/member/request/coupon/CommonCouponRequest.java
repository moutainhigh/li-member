package com.siyueli.platform.member.request.coupon;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommonCouponRequest {



    /**
     * 卡券任务ID
     */
    @ApiModelProperty(value = "卡券任务ID")
    private Long taskId;

    /**
     * 卡券类型
     */
    @ApiModelProperty(value = "卡券类型")
    private Integer taskTypeId;

    /**
     * 优惠券
     */
    @ApiModelProperty(value = "优惠券code")
    private String code;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID")
    private Long memberId;

    /**
     * 优惠券作用的订单号
     */
    @ApiModelProperty(value = "优惠券作用的订单号")
    private String orderNo;

    /**
     * 领取的来源平台
     */
    @ApiModelProperty(value = "领取的来源平台")
    private Integer source;

    /**
     * 优惠券状态
     */
    @ApiModelProperty(value = "优惠券状态")
    private Integer statusId;

    /**
     * 领取时间
     */
    @ApiModelProperty(value = "领取时间")
    private LocalDateTime receiptAt;

    /**
     * 使用时间
     */
    @ApiModelProperty(value = "使用时间")
    private LocalDateTime useAt;

    /**
     * 有效开始时间
     */
    @ApiModelProperty(value = "有效开始时间")
    private LocalDateTime validTimeStartAt;

    /**
     * 有效结束时间
     */
    @ApiModelProperty(value = "有效结束时间")
    private LocalDateTime validTimeEndAt;

}
