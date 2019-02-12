package com.yizhuoyang.qrcode.util;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtils {

    /**
     * 编码格式
     */
    private static final String CHARSET = "utf-8";

    /**
     * 二维码格式
     */
    private static final String FORMAT_NAME = "PNG";
    /**
     * 二维码尺寸
     */
    private static final int QRCODE_SIZE = 300;

    private static BufferedImage createImage(String content) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
                        : 0xFFFFFFFF);
            }
        }
        return image;
    }

    private static void encode(String content, ServletOutputStream stream) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content);
        ImageIO.write(image, FORMAT_NAME, stream);
    }

    public static void createQRImage(String content, HttpServletResponse response) {
        try {
            //不要缓存
            response.setDateHeader("expires", -1);
            //告诉所有浏览器不要缓存
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("content-type", "image/png");
            ServletOutputStream st = response.getOutputStream();
            encode(content, st);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
