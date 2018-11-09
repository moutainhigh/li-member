package com.siyueli.platform.member.vo.enjoycrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CommonEnjoyCrmRequest {

    @JsonProperty("UniqueKey")
    private String uniqueKey;
    @JsonProperty("ClientTime")
    private String clientTime;
    @JsonProperty("UserNo")
    private String userNo;
}
