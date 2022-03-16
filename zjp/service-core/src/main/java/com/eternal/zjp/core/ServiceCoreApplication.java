package com.eternal.zjp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther Eternal
 * @Date 2022/2/23
 */
@SpringBootApplication
@ComponentScan({"com.eternal.zjp", "com.eternal.common"})
public class ServiceCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }
}
