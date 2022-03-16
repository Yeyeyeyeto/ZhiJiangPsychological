package com.eternal.zjp.core.controller.api;


import com.eternal.common.exception.Assert;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.common.utils.RegexValidateUtils;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.pojo.vo.LoginVO;
import com.eternal.zjp.core.pojo.vo.RegisterVO;
import com.eternal.zjp.core.pojo.vo.UserIndexVO;
import com.eternal.zjp.core.pojo.vo.UserVO;
import com.eternal.zjp.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Eternal
 * @since 2022-03-09
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/api/core/user")
@Slf4j
public class UserController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserService userService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO) {
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        // 校验验证码是否正确
        String codeGen = (String) redisTemplate.opsForValue().get("zjp:sms:code:" + mobile);
        Assert.equals(code, codeGen, ResponseEnum.CODE_ERROR);

        // 注册
        userService.register(registerVO);

        return R.ok().message("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        String ip = request.getRemoteAddr();
        UserVO userVO = userService.login(loginVO, ip);

        return R.ok().data("user", userVO);
    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean result = JwtUtils.checkToken(token);

        if (result) {
            return R.ok();
        } else {
            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
        }
    }

    @ApiOperation("检验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile) {
        return userService.checkMobile(mobile);
    }

    @ApiOperation("获取个人空间用户信息")
    @GetMapping("/auth/getIndexUserInfo")
    public R getIndexUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);
        UserIndexVO userIndexVO = userService.getIndexUserInfo(userId);
        return R.ok().data("userIndexVO", userIndexVO);
    }

}

