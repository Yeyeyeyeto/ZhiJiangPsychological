package com.eternal.zjp.ssm.service.impl;

import com.eternal.common.exception.Assert;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.ssm.service.SmsService;
import com.eternal.zjp.ssm.util.SmsProperties;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @auther shkstart
 * @create 2021-09-09-11:04
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public void send(String mobile, String[] params) {
        try {
            SmsSingleSender ssender = new SmsSingleSender(SmsProperties.SDKAPPID, SmsProperties.SDKAPPKEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", mobile, SmsProperties.TEMPLATEDID, params, SmsProperties.SMSSIGN, "", "");
            Assert.equals(0, result.result, ResponseEnum.RENCENT_SMS_ERROR);
        }catch (HTTPException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
