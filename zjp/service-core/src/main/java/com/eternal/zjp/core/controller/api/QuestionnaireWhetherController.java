package com.eternal.zjp.core.controller.api;


import com.eternal.common.result.R;
import com.eternal.zjp.core.service.QuestionnaireWhetherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 是否型问卷表 前端控制器
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Api(tags = "是否型问卷接口")
@RestController
@RequestMapping("/api/core/questionnaireWhether")
@Slf4j
public class QuestionnaireWhetherController {

    @Resource
    private QuestionnaireWhetherService questionnaireWhetherService;

    @ApiOperation("问卷提交")
    @PostMapping("/submit")
    public R submit(@RequestBody Map<String, Object> maps) {

        Integer questionnaireId = (Integer) maps.get("key1");

        System.out.println(questionnaireId);

        ArrayList<Map<String, Integer>> radioList = (ArrayList<Map<String, Integer>>) maps.get("key2");

        for (Map<String, Integer> map : radioList) {
            int index = 0, value = 0;
            index = map.get("index");
            value = map.get("value");

            System.out.println(index + "  " + value);

        }

        return R.ok().message("提交成功");
    }


}