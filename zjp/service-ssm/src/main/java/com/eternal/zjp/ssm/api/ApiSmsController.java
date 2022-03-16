package com.eternal.zjp.ssm.api;


import com.eternal.common.exception.Assert;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.common.utils.RandomUtils;
import com.eternal.common.utils.RegexValidateUtils;
import com.eternal.zjp.ssm.client.CoreUserInfoClient;
import com.eternal.zjp.ssm.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @auther shkstart
 * @create 2021-09-09-17:42
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
@Slf4j
//@CrossOrigin
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号", required = true)
            @PathVariable String mobile){

        //MOBILE_NULL_ERROR(-202, "手机号不能为空"),
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //MOBILE_ERROR(-203, "手机号不正确")
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        // 判断手机号是否已经注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
        log.info("result = " + result);
        Assert.isTrue(result == false, ResponseEnum.MOBILE_EXIST_ERROR);

        //生成验证码
        String code = RandomUtils.getFourBitRandom();
        String[] params = new String[2];
        params[0] = code;
        params[1] = "5";
        //发送短信
        smsService.send(mobile, params);

        //将验证码存入redis
        redisTemplate.opsForValue().set("zjp:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }

}
