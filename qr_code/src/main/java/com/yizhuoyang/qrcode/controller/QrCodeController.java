package com.yizhuoyang.qrcode.controller;

import com.yizhuoyang.qrcode.util.QRCodeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "qrcontroller")
public class QrCodeController {

    @GetMapping("getQRCode")
    public void getQRCode(HttpServletResponse response) {
        String text = "http://www.baidu.com";
        QRCodeUtils.createQRImage(text, response);
    }
}
