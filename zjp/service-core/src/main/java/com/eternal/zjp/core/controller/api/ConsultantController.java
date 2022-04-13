package com.eternal.zjp.core.controller.api;


import com.eternal.common.exception.Assert;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.common.utils.RegexValidateUtils;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.pojo.vo.*;
import com.eternal.zjp.core.service.ConsultantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 咨询师表 前端控制器
 * </p>
 *
 * @author Eternal
 * @since 2022-03-14
 */
@Api(tags = "咨询师接口")
@RestController
@RequestMapping("/api/core/consultant")
@Slf4j
public class ConsultantController {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private ConsultantService consultantService;

    @ApiOperation("咨询师注册")
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
        consultantService.register(registerVO);

        return R.ok().message("注册成功");
    }

    @ApiOperation("咨询师登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        String ip = request.getRemoteAddr();
        UserVO userVO = consultantService.login(loginVO, ip);

        return R.ok().data("consultant", userVO);
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
        return consultantService.checkMobile(mobile);
    }

    @ApiOperation("获取个人空间用户信息")
    @GetMapping("/auth/getIndexConsultantInfo")
    public R getIndexUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);
        ConsultantIndexVO consultantIndexVO = consultantService.getIndexUserInfo(userId);
        return R.ok().data("consultantIndexVO", consultantIndexVO);
    }

    @ApiOperation("获取咨询师认证状态")
    @GetMapping("/auth/getConsultantStatus")
    public R getBorrowerStatus(HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);
        Integer status = consultantService.getAuthStatusById(userId);
        return R.ok().data("authStatus", status);
    }

    @ApiOperation("保存咨询师信息")
    @PostMapping("/auth/save")
    public R save(@RequestBody ConsultantVO consultantVO, HttpServletRequest request) {
        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);
        consultantService.saveConsultantVOById(consultantVO, userId);
        return R.ok().message("信息提交成功");
    }

}

