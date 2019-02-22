package com.yizhuoyang.qrcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  该模块仅仅是为了生成二维码，在controller中改写
 */
@SpringBootApplication
public class QrCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrCodeApplication.class, args);
    }

}

