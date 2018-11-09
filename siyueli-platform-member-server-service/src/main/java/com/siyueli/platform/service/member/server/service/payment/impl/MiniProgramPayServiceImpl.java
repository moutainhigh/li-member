package com.siyueli.platform.service.member.server.service.payment.impl;

import com.siyueli.platform.member.vo.payment.OrderQueryResultVo;
import com.siyueli.platform.member.vo.payment.OrderQueryVo;
import com.siyueli.platform.service.member.server.config.WeixinConfig;
import com.siyueli.platform.service.member.server.constant.WeixinUrl;
import com.siyueli.platform.service.member.server.service.payment.MiniProgramPayService;
import com.siyueli.platform.service.member.server.util.*;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MiniProgramPayServiceImpl implements MiniProgramPayService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MiniProgramPayServiceImpl.class);

    @Autowired
    private WeixinConfig weixinConfig;

    @Override
    public OrderQueryResultVo orderQuery(OrderQueryVo vo, String keyParam) {
        String key = keyParam;
        if (StringUtils.isEmpty(key))
            key = weixinConfig.getKey();

        String nonceStr = RandomStringUtil.getRandomString(32);
        String signType = "MD5";
        if (StringUtils.isEmpty(vo.getAppid()))
            vo.setAppid(weixinConfig.getAppid());

        if (StringUtils.isEmpty(vo.getMch_id()))
            vo.setMch_id(weixinConfig.getMch_id());
        if (StringUtils.isEmpty(vo.getNonce_str()))
            vo.setNonce_str(nonceStr);

        if (StringUtils.isEmpty(vo.getSign_type()))
            vo.setSign_type(signType);

        Map map = null;
        try {
            map = ConvertUtil.objectToMap(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        LOGGER.info("sign: " + sign);
        sign = sign + "&key=" + key;
        sign = MD5Maker.getMD5(sign);
        vo.setSign(sign);

        XStream xstream = XStreamUtil.getXStream(OrderQueryVo.class);
        String xml = xstream.toXML(vo);
        String content = HttpUtils.httpsPostRequest(WeixinUrl.ORDER_QUERY_URL, xml);
        OrderQueryResultVo orderQueryResult = getOrderQueryResult(content);
        return orderQueryResult;
    }

    @Override
    public OrderQueryResultVo orderQuery(OrderQueryVo oq) {
        return orderQuery(oq, null);
    }

    @Override
    public OrderQueryResultVo orderQuery(String out_trade_no) {
        OrderQueryVo oq = new OrderQueryVo();
        oq.setOut_trade_no(out_trade_no);
        return orderQuery(oq);
    }

    private OrderQueryResultVo getOrderQueryResult(String content) {
        XStream xstream = XStreamUtil.getXStream(OrderQueryResultVo.class);
        OrderQueryResultVo result = (OrderQueryResultVo)xstream.fromXML(content);
        return result;
    }
}
