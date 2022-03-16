package com.eternal.zjp.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Auther Eternal
 * @Date 2022/3/10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTests {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void saveNum(){
        //向数据库中存储string类型的键值对, 过期时间5分钟
        redisTemplate.opsForValue().set("dict", "555", 5, TimeUnit.MINUTES);
    }

    @Test
    public void getDict(){
        String string = (String) redisTemplate.opsForValue().get("dict");
        System.out.println(string);
    }
}