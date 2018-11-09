package com.siyueli.platform.member.vo.enjoycrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseEnjoyCrmRequest extends CommonEnjoyCrmRequest {

    @JsonProperty("Tag")
    private String tag;
    @JsonProperty("ObjectData")
    private Object objectData;
}
