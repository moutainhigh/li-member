package com.siyueli.platform.member.dto.memberuser;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class BalanceInfoDto {
    private BigDecimal balance;

    private BigDecimal points;

    private LocalDateTime updateAt;

}
