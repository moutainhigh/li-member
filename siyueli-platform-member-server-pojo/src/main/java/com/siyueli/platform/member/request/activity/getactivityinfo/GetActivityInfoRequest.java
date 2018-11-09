package com.siyueli.platform.member.request.activity.getactivityinfo;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class GetActivityInfoRequest {
    @Min(value = 1, message = "id必须大于或等于1")
    private Long id;
}
