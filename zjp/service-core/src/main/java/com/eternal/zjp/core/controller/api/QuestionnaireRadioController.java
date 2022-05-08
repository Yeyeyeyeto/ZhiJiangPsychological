package com.eternal.zjp.core.controller.api;


import com.eternal.common.result.R;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.service.QuestionnaireRadioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Api(tags = "单选型问卷接口")
@RestController
@RequestMapping("/api/core/questionnaireRadio")
@Slf4j
public class QuestionnaireRadioController {

    @Resource
    private QuestionnaireRadioService questionnaireRadioService;

    @ApiOperation("问卷提交")
    @PostMapping("/submit")
    public R submit(@RequestBody Map<String, Object> maps, HttpServletRequest request) {

        Integer questionnaireId = (Integer) maps.get("key1");
        ArrayList<Map<String, Integer>> radioList = (ArrayList<Map<String, Integer>>) maps.get("key2");

        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);

        int score = questionnaireRadioService.calScore(questionnaireId, userId, radioList);


        return R.ok().message("提交成功").data("score", score);
    }

}

