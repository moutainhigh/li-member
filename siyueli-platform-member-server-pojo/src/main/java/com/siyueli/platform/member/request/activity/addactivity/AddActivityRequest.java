package com.siyueli.platform.member.request.activity.addactivity;

import com.siyueli.platform.member.request.activity.common.CommonActivityRequest;
import lombok.Data;

import java.util.List;

@Data
public class AddActivityRequest extends CommonActivityRequest {

    private List<AddActRoleInfoVo> actRoleInfoList;
}
