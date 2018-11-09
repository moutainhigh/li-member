package com.siyueli.platform.member.response.payment;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class GetAccountLogResponse {

    private Long id;

    private Long userId;

    /**
     * 积分
     */
    private BigDecimal points = new BigDecimal("0.0");

    /**
     * 余额
     */
    private BigDecimal balance = new BigDecimal("0.0");

    /**
     * 压金
     */
    private BigDecimal deposite = new BigDecimal("0.0");

    /**
     * 类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    @TableField("create_at")
    private LocalDateTime createAt = LocalDateTime.now();
}
