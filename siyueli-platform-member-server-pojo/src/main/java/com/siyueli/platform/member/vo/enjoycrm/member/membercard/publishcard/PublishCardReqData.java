package com.siyueli.platform.member.vo.enjoycrm.member.membercard.publishcard;

import com.siyueli.platform.member.vo.enjoycrm.BaseObjectData;
import lombok.Data;

@Data
public class PublishCardReqData extends BaseObjectData {
    private String strCardNo;

    private String strCardName;

    private String strType;
}
