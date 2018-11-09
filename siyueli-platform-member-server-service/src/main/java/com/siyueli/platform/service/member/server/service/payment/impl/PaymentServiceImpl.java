package com.siyueli.platform.service.member.server.service.payment.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.siyueli.platform.member.common.PageResponse;
import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.member.constants.RechargeOrderStatus;
import com.siyueli.platform.member.dto.memberuser.BalanceInfoDto;
import com.siyueli.platform.member.pojo.member.MemberAccountHistory;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.pojo.recharge.RechargeOrder;
import com.siyueli.platform.member.request.payment.GetAccountLogRequest;
import com.siyueli.platform.member.request.payment.RechargeRequest;
import com.siyueli.platform.member.response.payment.GetAccountLogResponse;
import com.siyueli.platform.member.response.payment.GetRechargeResultResponse;
import com.siyueli.platform.member.response.payment.RechargeResponse;
import com.siyueli.platform.member.vo.payment.*;
import com.siyueli.platform.service.member.server.config.MemberConfig2;
import com.siyueli.platform.service.member.server.config.WeixinConfig;
import com.siyueli.platform.service.member.server.constant.MemberServiceConstant;
import com.siyueli.platform.service.member.server.constant.WeixinUrl;
import com.siyueli.platform.service.member.server.service.payment.MiniProgramPayService;
import com.siyueli.platform.service.member.server.service.payment.PaymentService;
import com.siyueli.platform.service.member.server.service.recharge.RechargeOrderService;
import com.siyueli.platform.service.member.server.service.member.JedisClusterService;
import com.siyueli.platform.service.member.server.service.member.MemberAccountHistoryServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserTwoService;
import com.siyueli.platform.service.member.server.util.*;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private WeixinConfig weixinConfig;

    @Autowired
    private JedisClusterService jedisClusterService;

    @Autowired
    private MemberUserServiceContract memberUserServiceContract;

    @Autowired
    private MemberConfig2 memberConfig2;

    @Autowired
    private RechargeOrderService rechargeOrderService;

    @Autowired
    private MemberAccountHistoryServiceContract memberAccountHistoryServiceContract;

    @Autowired
    private MiniProgramPayService miniProgramPayService;

    @Autowired
    private MemberUserTwoService memberUserTwoService;


    @Override
    public ResponseData<RechargeResponse> recharge(RechargeRequest rechargeRequest) {
        String url = WeixinUrl.UNIFIED_ORDER_URL;
        String notifyUrl = memberConfig2.getApi_url() + weixinConfig.getRecharge_notify_url();
        String openid = null;
        MemberUser user = getUser();
        if (user != null) {
            openid = user.getOpenId();
        }

        if (StringUtils.isEmpty(openid)) {
            return ResponseData.build(ResponseBackCode.CUSTOM_NOTIFY.getValue(), "找不到openid");
        }

        String appid = weixinConfig.getAppid();
        String mch_id = weixinConfig.getMch_id();
        String key = weixinConfig.getKey();
        String nonceStr = RandomStringUtil.getRandomString(32);

        String out_trade_no = RandomStringUtil.getCurrentAndRandom("R");
        String body = "recharge";
        String signType = "MD5";

        // 创建充值订单
        addRechargeOrder(rechargeRequest, user, out_trade_no);

        UnifiedOrderVo vo = new UnifiedOrderVo();
        vo.setAppid(appid);
        vo.setMch_id(mch_id);
        vo.setDevice_info("WEB");
        vo.setNonce_str(nonceStr);
        vo.setSign_type(signType);
        vo.setBody(body);
        vo.setOut_trade_no(out_trade_no);
        vo.setFee_type("CNY");
        vo.setTotal_fee(String.valueOf(rechargeRequest.getTotal_fee()));
        vo.setNotify_url(notifyUrl);
        vo.setTrade_type("JSAPI");
        vo.setOpenid(openid);

        /*// 空值
        vo.setDetail(null);
        vo.setAttach(null);
        // 空值
        vo.setSpbill_create_ip(null);
        vo.setTime_start(null);
        vo.setTime_expire(null);
        vo.setGoods_tag(null);
        // 空值
        vo.setProduct_id(null);
        vo.setLimit_pay(null);*/

        Map map = null;
        try {
            map = ConvertUtil.objectToMap(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        System.out.println("sign: " + sign);
        sign = sign + "&key=" + key;
        System.out.println("sign2: " + sign);

        sign = MD5Maker.getMD5(sign);
        vo.setSign(sign);

        XStream xstream = XStreamUtil.getXStream(UnifiedOrderVo.class);
        String xml = xstream.toXML(vo);

        //System.out.println("xml内容: ");
        //System.out.println(xml);

        String content = HttpUtils.httpsPostRequest(url, xml);

        //System.out.println("content内容：" + content);

        UnifiedOrderResultVo result = getResult(content);
        RechargeResponse resp = new RechargeResponse();

        long timeStamp = System.currentTimeMillis() / 1000;
        resp.setNonceStr(nonceStr);
        resp.setTimeStamp(String.valueOf(timeStamp));
        resp.setSignType(signType);
        resp.setPkg("prepay_id=" + result.getPrepay_id());
        resp.setOrderNo(out_trade_no);
        String paySign = getResponsePaySign(resp);
        resp.setPaySign(paySign);

        ResponseData data = ResponseUtil.success();
        data.setData(resp);
        return data;
    }

    @Override
    public NotifyResultVo getNotifyResult(String content) {
        XStream xstream = XStreamUtil.getXStream(NotifyResultVo.class);
        NotifyResultVo result = (NotifyResultVo)xstream.fromXML(content);
        return result;
    }

    private UnifiedOrderResultVo getResult(String content) {
        XStream xstream = XStreamUtil.getXStream(UnifiedOrderResultVo.class);
        UnifiedOrderResultVo result = (UnifiedOrderResultVo)xstream.fromXML(content);
        return result;
    }

    private void addRechargeOrder(RechargeRequest rechargeRequest, MemberUser user, String orderNo) {
        LocalDateTime now = LocalDateTime.now();
        RechargeOrder rechargeOrder = new RechargeOrder();
        rechargeOrder.setAmount(CurrencyUtil.fenToYuan(new BigDecimal(rechargeRequest.getTotal_fee())));
        rechargeOrder.setCreatedAt(now);
        rechargeOrder.setOrderNo(orderNo);
        rechargeOrder.setStatus(RechargeOrderStatus.INIT.getValue());
        rechargeOrder.setUpdatedAt(now);
        rechargeOrder.setUserId(user.getId());

        rechargeOrderService.insertAllColumn(rechargeOrder);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String handleNotifyResult(NotifyResultVo notifyResult) {
        if (notifyResult != null && StringUtils.isNotEmpty(notifyResult.getSign())) {
            String sign = getSign(notifyResult);
            //通过验签
            if (notifyResult.getSign().equals(sign)) {
                LOGGER.info("验签通过");
                LOGGER.info("回调通知结果：" + notifyResult.getOut_trade_no() + "：" + notifyResult.getReturn_code());

                EntityWrapper<RechargeOrder> entityWrapper = new EntityWrapper<RechargeOrder>();
                entityWrapper.eq("order_no", notifyResult.getOut_trade_no());
                RechargeOrder rechargeOrder = rechargeOrderService.selectOne(entityWrapper);
                if (rechargeOrder != null) {
                    if (MemberServiceConstant.PAY_SUCCESS.equals(notifyResult.getReturn_code())) {
                        LOGGER.info("rechargeOrder.getAmount(): " + rechargeOrder.getAmount());
                        LOGGER.info("new BigDecimal(notifyResult.getTotal_fee()): " + new BigDecimal(notifyResult.getTotal_fee()));
                        BigDecimal fenAmount = CurrencyUtil.yuanToFen(rechargeOrder.getAmount());
                        if (fenAmount.compareTo(new BigDecimal(notifyResult.getTotal_fee())) == 0) {
                            OrderQueryResultVo orderQueryResult = miniProgramPayService.orderQuery(notifyResult.getOut_trade_no());
                            if (orderQueryResult != null && MemberServiceConstant.PAY_SUCCESS.equals(orderQueryResult.getReturn_code())
                                    && MemberServiceConstant.PAY_SUCCESS.equals(orderQueryResult.getResult_code())
                                    && MemberServiceConstant.PAY_SUCCESS.equals(orderQueryResult.getTrade_state())
                                    && fenAmount.compareTo(new BigDecimal(orderQueryResult.getTotal_fee())) == 0) {

                                return updateOrderAndResponse(rechargeOrder, RechargeOrderStatus.PAY_SUCCESS.getValue(), "OK", notifyResult);
                            } else {
                                return updateOrderAndResponse(rechargeOrder, RechargeOrderStatus.PAY_FAIL.getValue(), "查询的订单状态与通知结果的订单状态不一致", notifyResult);
                            }


                        } else {
                            return updateOrderAndResponse(rechargeOrder, RechargeOrderStatus.PAY_FAIL.getValue(), "订单金额不对", notifyResult);
                        }
                    } else {
                        return updateOrderAndResponse(rechargeOrder, RechargeOrderStatus.PAY_FAIL.getValue(), "支付失败", notifyResult);
                    }
                } else {
                    return getResponse("FAIL", "找不到订单");
                }

            }
        }
        // 失败
        return getResponse("FAIL", "验签失败");
    }

    @Override
    public ResponseData<GetRechargeResultResponse> getRechargeResult(String orderNo) {
        EntityWrapper<RechargeOrder> entityWrapper = new EntityWrapper<RechargeOrder>();
        entityWrapper.eq("order_no", orderNo);
        RechargeOrder rechargeOrder = rechargeOrderService.selectOne(entityWrapper);
        if (rechargeOrder != null) {
            GetRechargeResultResponse resp = new GetRechargeResultResponse();
            resp.setOrderNo(orderNo);
            resp.setStatus(rechargeOrder.getStatus());
            ResponseData data = ResponseUtil.success();
            data.setData(resp);
            return data;
        }
        return ResponseData.build(ResponseBackCode.CUSTOM_NOTIFY.getValue(), "找不到订单");
    }

    private String updateOrderAndResponse(RechargeOrder rechargeOrder, int status, String return_msg, NotifyResultVo notifyResult) {
        updateRechargeOrderAndBalance(rechargeOrder, status, return_msg, notifyResult);
        return getResponse(getReturn_code(status), return_msg);
    }

    private String getReturn_code(int status) {
        if (RechargeOrderStatus.PAY_SUCCESS.getValue() == status) {
            return "SUCCESS";
        }
        return "FAIL";
    }


    private void updateRechargeOrderAndBalance(RechargeOrder rechargeOrder, int status, String return_msg, NotifyResultVo notifyResult) {
        // 已经支付成功，不再处理
        if (RechargeOrderStatus.PAY_SUCCESS.getValue() == rechargeOrder.getStatus()) {
            System.out.println("已经支付成功，不再处理: " + rechargeOrder.getOrderNo());
            return;
        }

        LocalDateTime now =  LocalDateTime.now();
        rechargeOrder.setStatus(status);
        rechargeOrder.setUpdatedAt(now);
        rechargeOrder.setRemark(return_msg + "[" + notifyResult.getReturn_msg() + "]");
        rechargeOrderService.updateAllColumnById(rechargeOrder);

        if (RechargeOrderStatus.PAY_SUCCESS.getValue() == status) {
            // 同步问题，事务问题
            MemberUser memberUser = memberUserServiceContract.selectById(rechargeOrder.getUserId());
            if (memberUser != null) {
                /*memberUser.setBalance(memberUser.getBalance().add(rechargeOrder.getAmount()));
                memberUser.setPoints(memberUser.getPoints().add(new BigDecimal(memberConfig2.getRecharge_award_points())));
                memberUser.setUpdateAt(now);
                memberUserServiceContract.updateAllColumnById(memberUser);*/

                BalanceInfoDto balanceInfo = new BalanceInfoDto();
                balanceInfo.setBalance(rechargeOrder.getAmount());
                balanceInfo.setPoints(new BigDecimal(memberConfig2.getRecharge_award_points()));
                balanceInfo.setUpdateAt(now);
                memberUserTwoService.addBalanceAndPoints(memberUser.getId(), balanceInfo);

                addBalanceLog(rechargeOrder);
                awardPoints(rechargeOrder);
            }
        }


    }

    @Override
    public ResponseData<PageResponse<GetAccountLogResponse>> getAccountLog(GetAccountLogRequest getAccountLogRequest) {
        Long userId = getUserId();
        EntityWrapper<MemberAccountHistory> entityWrapper = new EntityWrapper<MemberAccountHistory>();
        Page<MemberAccountHistory> page = new Page<MemberAccountHistory>(getAccountLogRequest.getPage(), getAccountLogRequest.getSize());
        entityWrapper.eq("user_id", userId);
        entityWrapper.eq("type", getAccountLogRequest.getType());
        Page<MemberAccountHistory> pageResult = memberAccountHistoryServiceContract.selectPage(page, entityWrapper);

        PageResponse<GetAccountLogResponse> pageResponse = null;
        if (pageResult != null) {
            List<MemberAccountHistory> list = pageResult.getRecords();
            List<GetAccountLogResponse> resultList = null;
            try {
                resultList = ConvertUtil.convertList(GetAccountLogResponse.class, list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            pageResponse = ConvertUtil.getPageResponse(pageResult, resultList);

        }

        ResponseData data = ResponseUtil.success();
        data.setData(pageResponse);
        return data;
    }

    /**
     * 增加余额变更记录
     * @param rechargeOrder
     */
    private void addBalanceLog(RechargeOrder rechargeOrder) {
        MemberAccountHistory mah = new MemberAccountHistory();
        mah.setType(MemberConstants.TYPE_BALANCE);
        mah.setBalance(rechargeOrder.getAmount());
        mah.setUserId(rechargeOrder.getUserId());
        mah.setCreateAt(new Date());
        mah.setSmallType(MemberConstants.SMALL_TYPE_ONLINE_RECHARSE);
        memberAccountHistoryServiceContract.insert(mah);
    }

    /**
     * 送积分
     * @param rechargeOrder
     */
    private void awardPoints(RechargeOrder rechargeOrder) {
        MemberAccountHistory mah = new MemberAccountHistory();
        mah.setType(MemberConstants.TYPE_POINTS);
        mah.setPoints(new BigDecimal(memberConfig2.getRecharge_award_points()));
        mah.setUserId(rechargeOrder.getUserId());
        mah.setCreateAt(new Date());
        mah.setSmallType(MemberConstants.SMALL_TYPE_ACTIVITY_GIFT);
        memberAccountHistoryServiceContract.insert(mah);
    }

    private String getSign(NotifyResultVo result) {
        NotifyResultVo signResult = new NotifyResultVo();
        BeanUtils.copyProperties(result, signResult);
        signResult.setSign(null);

        Map map = null;
        try {
            map = ConvertUtil.objectToMap(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        sign = sign + "&key=" + weixinConfig.getKey();
        sign = MD5Maker.getMD5(sign);
        return sign;
    }

    private String getResponsePaySign(RechargeResponse resp) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("appId", weixinConfig.getAppid());
        map.put("timeStamp", resp.getTimeStamp());
        map.put("nonceStr", resp.getNonceStr());
        map.put("package", resp.getPkg());
        map.put("signType", resp.getSignType());

        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        sign = sign + "&key=" + weixinConfig.getKey();
        sign = MD5Maker.getMD5(sign);
        return sign;
    }

    private String getResponse(String return_code, String return_msg) {
        NotifyResultResponse resonse = new NotifyResultResponse();
        resonse.setReturn_code(return_code);
        resonse.setReturn_msg(return_msg);
        XStream xstream = XStreamUtil.getXStream(NotifyResultResponse.class);
        String xml = xstream.toXML(resonse);
        return xml;
    }

    private MemberUser getUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String userId = null;
        if (StringUtils.isNotEmpty(token)) {
            userId = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);
        }
        MemberUser user = null;
        if (StringUtils.isNotEmpty(userId)) {
            user = memberUserServiceContract.selectById(userId);
            return user;
        }

        return null;
    }

    private Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("token");
        String userId = null;
        if (StringUtils.isNotEmpty(token)) {
            userId = jedisClusterService.get(MemberConstants.REDIS_USER_SESSION_KEY_FRONT + ":" + token);
        }
        MemberUser user = null;
        if (StringUtils.isNotEmpty(userId)) {
            return new Long(userId);
        }
        return null;
    }
}
