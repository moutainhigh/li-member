package com.siyueli.platform.member.vo.enjoycrm.gettoken;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siyueli.platform.member.vo.enjoycrm.CommonEnjoyCrmRequest;
import lombok.Data;

@Data
public class GetTokenRequest extends CommonEnjoyCrmRequest {


    @JsonProperty("Token")
    private String token;

}
