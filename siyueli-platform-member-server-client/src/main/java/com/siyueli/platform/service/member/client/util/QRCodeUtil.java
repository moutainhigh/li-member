package com.siyueli.platform.service.member.client.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class QRCodeUtil {

    public static BufferedImage createQRCode(final String content) throws WriterException {
        BufferedImage image = null;
        // 二维码图片输出流
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        HashMap<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>();
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
        //bitMatrix = updateBit(bitMatrix, 10);
        image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        return image;
    }
}
