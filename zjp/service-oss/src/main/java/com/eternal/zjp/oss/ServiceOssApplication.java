package com.eternal.zjp.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther Eternal
 * @Date 2021/10/11
 */
@SpringBootApplication
@ComponentScan({"com.eternal.zjp", "com.eternal.common"})
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class, args);
    }

}
