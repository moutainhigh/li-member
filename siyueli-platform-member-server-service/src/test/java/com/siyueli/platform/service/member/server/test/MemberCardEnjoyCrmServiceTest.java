package com.siyueli.platform.service.member.server.test;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.publishcard.PublishCardReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querybalancechangedetail.QueryBalanceChangeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardaccountinfo.QueryCardAccountInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardconsumedetail.QueryCardConsumeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querycardinfo.QueryCardInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.queryconsumedetail.QueryConsumeDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.membercard.querypointdetail.QueryPointDetailReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberpoint.addpoint.AddPointRespData;
import com.siyueli.platform.service.member.server.MemberServerApplication;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberCardEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberInfoEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberPointEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.TokenEnjoyCrmService;
import com.siyueli.platform.service.member.server.util.FileReaderUtil;
import com.siyueli.platform.service.member.server.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
//properties = {"application.yml, application-siyueli.yml"},
@SpringBootTest(classes = MemberServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration
@ActiveProfiles("siyueli")
public class MemberCardEnjoyCrmServiceTest {

    private static final String ADD_POINT_FILE = "/enjoycrm/membercard/add_point.json";
    private static final String QUERY_CARD_INFO_FILE = "/enjoycrm/membercard/query_card_info.json";
    private static final String QUERY_CARD_ACCOUNT_INFO_FILE = "/enjoycrm/membercard/query_card_account_info.json";
    private static final String QUERY_BALANCE_CHANGE_DETAIL_FILE = "/enjoycrm/membercard/query_balance_change_detail.json";
    private static final String QUERY_CARD_CONSUME_DETAIL_FILE = "/enjoycrm/membercard/query_card_consume_detail.json";
    private static final String QUERY_POINT_DETAIL_FILE = "/enjoycrm/membercard/query_point_detail.json";
    private static final String QUERY_CONSUME_DETAIL_FILE = "/enjoycrm/membercard/query_consume_detail.json";
    private static final String PUBLISH_CARD_FILE = "/enjoycrm/membercard/publish_card.json";

    @Autowired
    private TokenEnjoyCrmService tokenEnjoyCrmService;

    @Autowired
    private MemberInfoEnjoyCrmService memberInfoEnjoyCrmService;

    @Autowired
    private MemberCardEnjoyCrmService memberCardEnjoyCrmService;

    @Autowired
    private MemberPointEnjoyCrmService memberPointEnjoyCrmService;

    @Test
    public void testAddPoint() {
        String content = FileReaderUtil.readFile(ADD_POINT_FILE);
        AddPointReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, AddPointReqData.class);
            BaseEnjoyCrmResponse<AddPointRespData> resp = memberPointEnjoyCrmService.addPoint(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryCardInfo() {
        String content = FileReaderUtil.readFile(QUERY_CARD_INFO_FILE);
        QueryCardInfoReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryCardInfoReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryCardInfo(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryCardAccountInfo() {
        String content = FileReaderUtil.readFile(QUERY_CARD_ACCOUNT_INFO_FILE);
        QueryCardAccountInfoReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryCardAccountInfoReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryCardAccountInfo(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryBalanceChangeDetail() {
        String content = FileReaderUtil.readFile(QUERY_BALANCE_CHANGE_DETAIL_FILE);
        QueryBalanceChangeDetailReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryBalanceChangeDetailReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryBalanceChangeDetail(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryCardConsumeDetail() {
        String content = FileReaderUtil.readFile(QUERY_CARD_CONSUME_DETAIL_FILE);
        QueryCardConsumeDetailReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryCardConsumeDetailReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryCardConsumeDetail(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryPointDetail() {
        String content = FileReaderUtil.readFile(QUERY_POINT_DETAIL_FILE);
        QueryPointDetailReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryPointDetailReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryPointDetail(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testQueryConsumeDetail() {
        String content = FileReaderUtil.readFile(QUERY_CONSUME_DETAIL_FILE);
        QueryConsumeDetailReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryConsumeDetailReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.queryConsumeDetail(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPublishCard() {
        String content = FileReaderUtil.readFile(PUBLISH_CARD_FILE);
        PublishCardReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, PublishCardReqData.class);
            BaseEnjoyCrmResponse resp = memberCardEnjoyCrmService.publishCard(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAll() {
        testAddPoint();
        testQueryCardInfo();
        testQueryCardAccountInfo();
        testQueryBalanceChangeDetail();
        testQueryCardConsumeDetail();
        testQueryPointDetail();
        testQueryConsumeDetail();
        testPublishCard();
    }
}
