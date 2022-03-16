package com.eternal.zjp.ssm.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @auther shkstart
 * @create 2021-09-09-8:25
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "rencent.sms")
public class SmsProperties implements InitializingBean {

    private int templateId;
    private int SDKAppId;
    private String SDKAppKey;
    private String smsSign;

    public static int TEMPLATEDID;
    public static int SDKAPPID;
    public static String SDKAPPKEY;
    public static String SMSSIGN;

    @Override
    public void afterPropertiesSet() throws Exception {
        TEMPLATEDID = templateId;
        SDKAPPID = SDKAppId;
        SDKAPPKEY = SDKAppKey;
        SMSSIGN = smsSign;
    }
}
