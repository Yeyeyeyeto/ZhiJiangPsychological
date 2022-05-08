package com.eternal.zjp.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.common.exception.Assert;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.core.mapper.QuestionnaireRadioMapper;
import com.eternal.zjp.core.pojo.entity.QuestionnaireRadio;
import com.eternal.zjp.core.pojo.entity.TestRecord;
import com.eternal.zjp.core.service.QuestionnaireRadioService;
import com.eternal.zjp.core.service.TestRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class QuestionnaireRadioServiceImpl extends ServiceImpl<QuestionnaireRadioMapper, QuestionnaireRadio> implements QuestionnaireRadioService {

    @Resource
    private QuestionnaireRadioMapper questionnaireRadioMapper;

    @Resource
    private TestRecordService testRecordService;

    @Override
    public int calScore(Integer questionnaireId, Integer userId, ArrayList<Map<String, Integer>> radioList) {

        QueryWrapper<QuestionnaireRadio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", questionnaireId).orderByAsc("question_num");
        List<QuestionnaireRadio> itemList = questionnaireRadioMapper.selectList(queryWrapper);

        int score = 0;

        if(itemList.size() != radioList.size()) Assert.notNull(null, ResponseEnum.QUESTION_NULL_ERROR);

        int i = 0;
        for (Map<String, Integer> map : radioList) {
            int index = 0, value = 0;
            index = map.get("index");
            value = map.get("value");
            System.out.println(index + ": " + value);

            JSONObject jsonObject = new JSONObject(new LinkedHashMap<>()).parseObject(itemList.get(i).getRadioOptions());
            int k = 0;
            for (Object map2 : jsonObject.entrySet()){
                int num = Integer.parseInt(((Map.Entry)map2).getValue().toString());
                if (k == value) {
                    score += num;
                    k = 0;
                } else {
                    k++;
                }
            }

            i++;
        }

        TestRecord testRecord = new TestRecord();
        testRecord.setUserId(userId);
        testRecord.setQuestionnaireId(questionnaireId);
        testRecord.setScore(score);
        testRecordService.save(testRecord);

        return score;
    }
}
