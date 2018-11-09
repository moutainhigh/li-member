package com.siyueli.platform.member.vo.enjoycrm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BaseEnjoyCrmResponse<T> {

    @JsonProperty("UniqueKey")
    private String uniqueKey;
    @JsonProperty("MethodName")
    private String methodName;
    @JsonProperty("SourceMethodName")
    private String sourceMethodName;
    @JsonProperty("Tag")
    private String tag;
    @JsonProperty("UserState")
    private String userState;
    @JsonProperty("Exception")
    private EnjoyCrmException exception;
    @JsonProperty("HasException")
    private Boolean hasException;
    @JsonProperty("ObjectData")
    private T objectData;
}
