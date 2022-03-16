package com.eternal.zjp.ssm.client.fallback;

import com.eternal.zjp.ssm.client.CoreUserInfoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther Eternal
 * @Date 2021/10/16
 */
@Service
@Slf4j
public class CoreUserInfoClientFallback implements CoreUserInfoClient {
    @Override
    public boolean checkMobile(String mobile) {
        log.error("远程调用失败，服务熔断。");
        return false;   // 手机号不重复
    }
}
