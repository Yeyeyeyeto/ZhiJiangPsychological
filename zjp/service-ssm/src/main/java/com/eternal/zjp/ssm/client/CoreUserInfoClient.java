package com.eternal.zjp.ssm.client;


import com.eternal.zjp.ssm.client.fallback.CoreUserInfoClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther Eternal
 * @Date 2021/10/16
 */
@FeignClient(value = "service-core", fallback = CoreUserInfoClientFallback.class)
public interface CoreUserInfoClient {

    @GetMapping("/api/core/user/checkMobile/{mobile}")
    boolean checkMobile(@PathVariable String mobile);

}
