package com.siyueli.platform.service.member.server.service.paymentcode.impl;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.util.ResponseUtil;
import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.siyueli.platform.member.constants.CharsetNameConstant;
import com.siyueli.platform.member.constants.MemberConstants;
import com.siyueli.platform.member.dto.memberuser.BalanceInfoDto;
import com.siyueli.platform.member.pojo.member.MemberAccountHistory;
import com.siyueli.platform.member.pojo.member.MemberUser;
import com.siyueli.platform.member.pojo.paymentcode.PaymentCode;
import com.siyueli.platform.member.vo.paymentcode.PaymentCodeSignVo;
import com.siyueli.platform.service.member.server.config.MemberConfig2;
import com.siyueli.platform.service.member.server.mapper.paymentcode.PaymentCodeMapper;
import com.siyueli.platform.service.member.server.service.payment.impl.PaymentServiceImpl;
import com.siyueli.platform.service.member.server.service.paymentcode.PaymentCodeServiceContract;
import com.siyueli.platform.service.member.server.service.member.JedisClusterService;
import com.siyueli.platform.service.member.server.service.member.MemberAccountHistoryServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserServiceContract;
import com.siyueli.platform.service.member.server.service.member.MemberUserTwoService;
import com.siyueli.platform.service.member.server.util.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * <p>
 * 付款码表 服务实现类
 * </p>
 *
 * @author Sipin ERP Development Team
 */
@Primary
@Service
public class PaymentCodeService extends ServiceImpl<PaymentCodeMapper, PaymentCode> implements IService<PaymentCode>, PaymentCodeServiceContract {

    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentCodeService.class);

    @Autowired
    private MemberConfig2 memberConfig2;

    @Autowired
    private JedisClusterService jedisClusterService;

    @Autowired
    private MemberUserServiceContract memberUserServiceContract;

    @Autowired
    private MemberAccountHistoryServiceContract memberAccountHistoryServiceContract;

    @Autowired
    private UserLoginUtil userLoginUtil;

    @Autowired
    private MemberUserTwoService memberUserTwoService;

    @Override
    public ResponseData getPaymentCode() {
        LOGGER.info("getPaymentCode");

        Long userId = getUserId();
        UUID uuid = UUID.randomUUID();
        String content = uuid.toString();
        String paymentCodeStr = AESUtil.encrypt(content,memberConfig2.getAes_key());
        if (StringUtils.isEmpty(paymentCodeStr))
            return ResponseUtil.fail();

        String paymentCodeJsonStr = null;
        String paymentCodeBase64 = null;
        PaymentCodeSignVo pcsv = new PaymentCodeSignVo();
        pcsv.setPaymentCode(paymentCodeStr);
        try {
            String sign = getPaymentCodeSign(pcsv);
            pcsv.setSign(sign);
            paymentCodeJsonStr = JsonUtil.toJsonString(pcsv);
            paymentCodeBase64 = Base64Util.encodeBase64(paymentCodeJsonStr.getBytes(CharsetNameConstant.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime validTime = LocalDateTime.now().plus(1, ChronoUnit.MINUTES);
        PaymentCode paymentCode = new PaymentCode();
        paymentCode.setCreatedAt(now);
        paymentCode.setIsUsed(0);
        paymentCode.setPaymentCode(content);
        paymentCode.setUpdatedAt(now);
        paymentCode.setUserId(userId);
        paymentCode.setValidTime(validTime);

        insertAllColumn(paymentCode);

        return ResponseUtil.success(paymentCodeBase64);
    }

    /**
     * 得到付款码的签名
     * @param pcsv
     * @return
     * @throws Exception
     */
    private String getPaymentCodeSign(PaymentCodeSignVo pcsv) throws Exception {
        Map map = ConvertUtil.objectToMap(pcsv);
        Map sortedMap = MapSortUtil.sortMapByKey(map);
        String sign = ConvertUtil.getSignatureParams(sortedMap);
        System.out.println("sign: " + sign);
        sign = sign + "&key=" + memberConfig2.getMd5_key();
        sign = MD5Maker.getMD5(sign);
        return sign;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData deductMoney(String paymentCode, String orderNo) {
        if (StringUtils.isEmpty(paymentCode) || StringUtils.isEmpty(orderNo))
            return ResponseUtil.fail("参数不对");

        PaymentCodeSignVo pcsv = null;
        try {
            byte[] paymentCodeBytes = Base64Util.decodeBase64(paymentCode);
            String paymentCodeJsonStr = new String(paymentCodeBytes, CharsetNameConstant.UTF_8);
            pcsv = JsonUtil.jsonToObject(paymentCodeJsonStr, PaymentCodeSignVo.class);
            String sign = pcsv.getSign();
            pcsv.setSign(null);
            String mySign = getPaymentCodeSign(pcsv);

            if (!mySign.equals(sign)) {
                return ResponseUtil.fail("验签失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail();
        }


        String paymentCode2 = null;
        try {
            paymentCode2 = AESUtil.decrypt(pcsv.getPaymentCode(),memberConfig2.getAes_key());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.fail("付款码不对");
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("payment_code", paymentCode2);
        List<PaymentCode> list = selectByMap(map);
        PaymentCode pc = null;
        if (list == null || list.isEmpty()) {
            return ResponseUtil.fail("找不到付款码");
        } else {
            pc = list.get(0);
            if (pc.getIsUsed() == 1) {
                return ResponseUtil.fail("付款码已经使用");
            }

            System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
            System.out.println("validTime: " + pc.getValidTime().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            // 已经过期
            if (System.currentTimeMillis() > pc.getValidTime().toInstant(ZoneOffset.of("+8")).toEpochMilli()) {
                return ResponseUtil.fail("付款码已经过期");
            }
        }

        //查找订单
        Map orderMap = getOrder(orderNo);
        if (orderMap == null) {
            return ResponseUtil.fail("找不到订单");
        }
        BigDecimal amount = (BigDecimal)orderMap.get("amount");

        MemberUser memberUser = memberUserServiceContract.selectById(pc.getUserId());
        if (memberUser == null) {
            return ResponseUtil.fail("找不到用户");
        }
        if (memberUser.getBalance() == null || memberUser.getBalance().compareTo(amount) == -1) {
            return ResponseUtil.fail("用户余额不足");
        }

        // 扣款
        deductUserMoney(memberUser, amount, pc, orderNo);


        return ResponseUtil.success();

    }


    private void deductUserMoney(MemberUser memberUser, BigDecimal amount, PaymentCode pc, String orderNo) {
        // 同步问题，事务问题
        if (memberUser != null) {
            LocalDateTime now = LocalDateTime.now();
            /*memberUser.setBalance(memberUser.getBalance().subtract(amount));
            memberUser.setUpdateAt(LocalDateTime.now());
            memberUserServiceContract.updateAllColumnById(memberUser);*/

            BalanceInfoDto balanceInfo = new BalanceInfoDto();
            balanceInfo.setBalance(amount.multiply(new BigDecimal(-1)));
            balanceInfo.setUpdateAt(now);
            memberUserTwoService.addBalanceAndPoints(memberUser.getId(), balanceInfo);

            addBalanceLog(pc.getUserId(), amount);

            pc.setIsUsed(1);
            pc.setOrderNo(orderNo);
            pc.setAmount(amount);
            pc.setUpdatedAt(now);

            updateAllColumnById(pc);

        }
    }

    /**
     * 增加余额变更记录
     */
    private void addBalanceLog(Long userId, BigDecimal amount) {
        MemberAccountHistory mah = new MemberAccountHistory();
        mah.setType(MemberConstants.TYPE_BALANCE);
        mah.setBalance(amount);
        mah.setUserId(userId);
        mah.setCreateAt(new Date());
        mah.setSmallType(MemberConstants.SMALL_TYPE_CONSUME_DEDUCE);
        memberAccountHistoryServiceContract.insert(mah);
    }

    private Map getOrder(String orderNo) {
        Map map = new HashMap();

        map.put("orderNo", orderNo);
        map.put("amount", new BigDecimal(1));
        return map;
    }

    private Long getUserId() {
        return userLoginUtil.getUserId();
    }
}
