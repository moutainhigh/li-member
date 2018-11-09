package com.siyueli.platform.member.vo.enjoycrm;

import lombok.Data;

@Data
public class EnjoyCrmException {
    private Boolean HasException;

    private Integer ExceptionType;

    private String Code;

    private String Message;

    private String StackTrace;
}
