package com.eternal.zjp.ssm.service;

/**
 * @auther shkstart
 * @create 2021-09-09-10:56
 */
public interface SmsService {

    void send(String mobile, String[] param);

}
