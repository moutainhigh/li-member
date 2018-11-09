package com.siyueli.platform.service.member.server.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.gettoken.GetTokenResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail.LstDeposits;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail.QueryBalanceChangeDetailRespData;
import com.siyueli.platform.service.member.server.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    @Test
    public void testGen() {
        BaseEnjoyCrmResponse<QueryMemberInfoReqData> resp = new BaseEnjoyCrmResponse<QueryMemberInfoReqData>();
        resp.setHasException(false);
        resp.setUniqueKey("llldddds");
        QueryMemberInfoReqData data = new QueryMemberInfoReqData();
        data.setStrCardNo("kkkss");
        data.setStrAddress("ksdooouuuuu");
        resp.setObjectData(data);
        try {
            String jsonStr = JsonUtil.toJsonString(resp);
            BaseEnjoyCrmResponse<QueryMemberInfoReqData> resp2 = JsonUtil.jsonToObject(jsonStr, BaseEnjoyCrmResponse.class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGen2() {
        GetTokenResponse resp = new GetTokenResponse();
        resp.setUniqueKey("llldddds");
        resp.setObjectData("q1122kljluuuyyt");
        try {
            String jsonStr = JsonUtil.toJsonString(resp);
            GetTokenResponse resp2 = JsonUtil.jsonToObject(jsonStr, GetTokenResponse.class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGen3() {
        TestQueryMemberInfoResponse resp = new TestQueryMemberInfoResponse();
        resp.setUniqueKey("llldddds");
        QueryMemberInfoReqData data = new QueryMemberInfoReqData();
        data.setStrCardNo("kkkss");
        data.setStrAddress("ksdooouuuuu");
        resp.setObjectData(data);
        try {
            String jsonStr = JsonUtil.toJsonString(resp);
            TestQueryMemberInfoResponse resp2 = JsonUtil.jsonToObject(jsonStr, TestQueryMemberInfoResponse.class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGen4() {
        TestQueryMemberInfoResponse resp = new TestQueryMemberInfoResponse();
        resp.setUniqueKey(null);
        QueryMemberInfoReqData data = new QueryMemberInfoReqData();
        data.setStrCardNo("kkkss");
        data.setStrAddress("ksdooouuuuu");
        resp.setObjectData(data);
        resp.setHasException(null);
        try {
            String jsonStr = JsonUtil.toJsonString(resp);
            JsonNode jsonNode = JsonUtil.parseJson(jsonStr);
            String ukStr = jsonNode.path("HasEx2ception").asText();
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGen5() {
        QueryBalanceChangeDetailRespData resp = new QueryBalanceChangeDetailRespData();
        resp.setISumCount(2);
        List<LstDeposits> depoList = new ArrayList<LstDeposits>();
        LstDeposits depo = new LstDeposits();
        depo.setDecAmount(new BigDecimal(1));
        depo.setStrCardNo("kkksss");
        depoList.add(depo);
        resp.setLstDeposits(depoList);
        try {
            String jsonStr = JsonUtil.toJsonString(resp);
            QueryBalanceChangeDetailRespData data = JsonUtil.jsonToObject(jsonStr, QueryBalanceChangeDetailRespData.class);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGen6() {
        /*QueryBalanceChangeDetailRespData resp = new QueryBalanceChangeDetailRespData();
        resp.setISumCount(2);*/
        List<LstDeposits> depoList = new ArrayList<LstDeposits>();
        LstDeposits depo = new LstDeposits();
        depo.setDecAmount(new BigDecimal(1));
        depo.setStrCardNo("kkksss");
        depoList.add(depo);
        //resp.setLstDeposits(depoList);
        try {
            String jsonStr = JsonUtil.toJsonString(depoList);
            QueryBalanceChangeDetailRespData data = JsonUtil.jsonToObject(jsonStr, QueryBalanceChangeDetailRespData.class);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJsonNode() {
        List<String> list = new ArrayList<String>();
        list.add("kkkk");
        list.add("ddddd");
        list.add("ccccc");

        String jsonStr = null;
        try {
            jsonStr = JsonUtil.toJsonString(list);
            JsonNode jsonNode = JsonUtil.jsonToObject(jsonStr, JsonNode.class);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
