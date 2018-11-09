package com.siyueli.platform.member.vo.payment;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

@Data
@XStreamAlias("xml")
public class NotifyResultResponse {

    private String return_code;

    private String return_msg;
}
