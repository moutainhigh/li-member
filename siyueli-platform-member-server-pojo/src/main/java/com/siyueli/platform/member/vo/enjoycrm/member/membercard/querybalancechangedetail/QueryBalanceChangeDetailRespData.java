package com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail;

import lombok.Data;

import java.util.List;

@Data
public class QueryBalanceChangeDetailRespData {
    private Integer iSumCount;
    private List<LstDeposits> lstDeposits;
}
