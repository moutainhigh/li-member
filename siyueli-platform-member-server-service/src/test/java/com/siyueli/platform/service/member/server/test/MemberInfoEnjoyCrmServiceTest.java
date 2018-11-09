package com.siyueli.platform.service.member.server.test;

import com.siyueli.platform.member.vo.enjoycrm.BaseEnjoyCrmResponse;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.addorupdatememberextinfo.AddOrUpdateMemberExtInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.addorupdatememberextinfo.AddOrUpdateMemberExtInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember.BindMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.bindmember.BindMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.querymemberinfo.QueryMemberInfoRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.registermember.RegisterMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.registermember.RegisterMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.unbindmember.UnbindMemberReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.unbindmember.UnbindMemberRespData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo.UpdateMemberInfoReqData;
import com.siyueli.platform.member.vo.enjoycrm.member.memberbaseinfo.updatememberinfo.UpdateMemberInfoRespData;
import com.siyueli.platform.service.member.server.MemberServerApplication;
import com.siyueli.platform.service.member.server.config.EnjoyCrmConfig;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberPointEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.TokenEnjoyCrmService;
import com.siyueli.platform.service.member.server.service.enjoycrm.MemberInfoEnjoyCrmService;
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
public class MemberInfoEnjoyCrmServiceTest {

    private static final String QUERY_MEMBER_INFO_FILE = "/enjoycrm/memberinfo/query_member_info.json";
    private static final String REGISTER_MEMBER_INFO_FILE = "/enjoycrm/memberinfo/register_member_info.json";
    private static final String UPDATE_MEMBER_INFO_FILE = "/enjoycrm/memberinfo/update_member_info.json";
    private static final String BIND_MEMBER_INFO_FILE = "/enjoycrm/memberinfo/bind_member_info.json";
    private static final String UNBIND_MEMBER_FILE = "/enjoycrm/memberinfo/unbind_member.json";
    private static final String UPDATE_MEMBER_EXT_INFO_FILE = "/enjoycrm/memberinfo/update_member_ext_info.json";

    @Autowired
    private TokenEnjoyCrmService tokenEnjoyCrmService;

    @Autowired
    private MemberInfoEnjoyCrmService memberInfoEnjoyCrmService;



    //@Before
    public void init() {
        String service_url = System.getenv("service_url");
        String userno = System.getenv("userno");
        String key = System.getenv("key");

        EnjoyCrmConfig enjoyCrmConfig = new EnjoyCrmConfig();
        enjoyCrmConfig.setService_url(service_url);
        enjoyCrmConfig.setUserno(userno);
        enjoyCrmConfig.setKey(key);

    }

    @Test
    public void testGetToken() {
        tokenEnjoyCrmService.getToken(false);
    }

    @Test
    public void testQueryMemberInfo() {
        String content = FileReaderUtil.readFile(QUERY_MEMBER_INFO_FILE);
        QueryMemberInfoReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, QueryMemberInfoReqData.class);
            BaseEnjoyCrmResponse<QueryMemberInfoRespData> resp = memberInfoEnjoyCrmService.queryMemberInfo(data);
            QueryMemberInfoRespData respData = resp.getObjectData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRegisterMember() {
        String content = FileReaderUtil.readFile(REGISTER_MEMBER_INFO_FILE);
        try {
            RegisterMemberReqData data = JsonUtil.jsonToObject(content, RegisterMemberReqData.class);
            BaseEnjoyCrmResponse<RegisterMemberRespData> respData = memberInfoEnjoyCrmService.registerMember(data);
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdateMemberInfo() {
        String content = FileReaderUtil.readFile(UPDATE_MEMBER_INFO_FILE);
        UpdateMemberInfoReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, UpdateMemberInfoReqData.class);
            BaseEnjoyCrmResponse<UpdateMemberInfoRespData> respData = memberInfoEnjoyCrmService.updateMemberInfo(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBindMember() {
        String content = FileReaderUtil.readFile(BIND_MEMBER_INFO_FILE);
        BindMemberReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, BindMemberReqData.class);
            BaseEnjoyCrmResponse<BindMemberRespData> resp = memberInfoEnjoyCrmService.bindMember(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUnbindMember() {
        String content = FileReaderUtil.readFile(UNBIND_MEMBER_FILE);
        UnbindMemberReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, UnbindMemberReqData.class);
            BaseEnjoyCrmResponse<UnbindMemberRespData> resp = memberInfoEnjoyCrmService.unbindMember(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testAddOrUpdateMemberExtInfo() {
        String content = FileReaderUtil.readFile(UPDATE_MEMBER_EXT_INFO_FILE);
        AddOrUpdateMemberExtInfoReqData data = null;
        try {
            data = JsonUtil.jsonToObject(content, AddOrUpdateMemberExtInfoReqData.class);
            BaseEnjoyCrmResponse<AddOrUpdateMemberExtInfoRespData> resp = memberInfoEnjoyCrmService.addOrUpdateMemberExtInfo(data);
            Assert.assertFalse(resp.getHasException());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void testAll() {
        testQueryMemberInfo();
        testRegisterMember();
        testUpdateMemberInfo();
        testBindMember();
        testUnbindMember();
        testAddOrUpdateMemberExtInfo();
    }
}
