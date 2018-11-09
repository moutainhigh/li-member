package com.siyueli.platform.service.member.client.controller.paymentcode;

import cn.siyue.platform.base.ResponseData;
import cn.siyue.platform.constants.ResponseBackCode;
import cn.siyue.platform.httplog.annotation.LogAnnotation;
import cn.siyue.platform.util.ResponseUtil;
import com.google.zxing.WriterException;
import com.siyueli.platform.member.request.paymentcode.deductmoney.DeductMoneyRequest;
import com.siyueli.platform.service.member.client.controller.BaseController;
import com.siyueli.platform.service.member.client.service.paymentcode.PaymentCodeService;
import com.siyueli.platform.service.member.client.util.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Api(tags = "斯越里_付款码接口")
@RestController
@RequestMapping(path = "/paymentCode")
public class PaymentCodeController extends BaseController {

    @Autowired
    private PaymentCodeService paymentCodeService;

    @LogAnnotation
    @ApiOperation(nickname = "getPaymentCode",value = "得到付款码")
    @PostMapping("/getPaymentCode")
    public ResponseData getPaymentCode(HttpServletResponse response) {
        ResponseData respData = paymentCodeService.getPaymentCode();
        if (respData != null) {
            if (respData.getCode() != null && ResponseBackCode.SUCCESS.getValue() == respData.getCode()) {
                if (respData.getData() != null && StringUtils.isNotEmpty(respData.getData().toString())) {
                    try {
                        BufferedImage image = QRCodeUtil.createQRCode(respData.getData().toString());
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(image, "png", baos);
                        String imageStr = Base64.encodeBase64String(baos.toByteArray());

                        return ResponseUtil.success(imageStr);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return ResponseUtil.build(respData.getCode(), respData.getMsg());
        }
        return ResponseUtil.fail();
    }

    @LogAnnotation
    @ApiOperation(nickname = "deductMoney",value = "扣款")
    @PostMapping("/deductMoney")
    public ResponseData deductMoney(@RequestBody DeductMoneyRequest deductMoneyRequest, BindingResult result) {
        ResponseData errorResponse = getErrorResponse(result);
        if (errorResponse != null)
            return errorResponse;


        ResponseData respData = paymentCodeService.deductMoney(deductMoneyRequest);
        return respData;
    }
}
