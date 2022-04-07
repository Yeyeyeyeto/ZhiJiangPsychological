package com.eternal.zjp.core.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eternal.common.exception.Assert;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
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
import java.util.List;
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
        ArrayList<Map<String, Integer>> radioList = (ArrayList<Map<String, Integer>>) maps.get("key2");

//        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);


        QueryWrapper<QuestionnaireWhether> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", questionnaireId).orderByAsc("question_num");
        List<QuestionnaireWhether> itemList = questionnaireWhetherService.list(queryWrapper);

        int count = 0;

        if(itemList.size() != radioList.size()) Assert.notNull(null, ResponseEnum.QUESTION_NULL_ERROR);


        for (Map<String, Integer> map : radioList) {
            int index = 0, value = 0;
            index = map.get("index");
            value = map.get("value");

//            System.out.println(index + ": " + value);

            if (value == 1) {
                count += itemList.get(index-1).getTrueScore();
            } else {
                count += itemList.get(index-1).getFalseScore();
            }
        }

        System.out.println(count);

        return R.ok().message("提交成功").data("score", count);
    }

}