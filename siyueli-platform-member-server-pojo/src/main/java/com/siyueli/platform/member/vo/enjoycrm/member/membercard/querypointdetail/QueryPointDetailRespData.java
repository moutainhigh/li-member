package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querypointdetail;

import lombok.Data;

import java.util.List;

@Data
public class QueryPointDetailRespData {
    private Integer iSumCount;

    private List<LstScores> lstScores;
}
