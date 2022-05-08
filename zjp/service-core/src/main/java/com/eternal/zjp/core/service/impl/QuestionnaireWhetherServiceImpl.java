package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.common.exception.Assert;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.core.mapper.QuestionnaireMapper;
import com.eternal.zjp.core.mapper.QuestionnaireRadioMapper;
import com.eternal.zjp.core.mapper.QuestionnaireWhetherMapper;
import com.eternal.zjp.core.pojo.entity.Questionnaire;
import com.eternal.zjp.core.pojo.entity.QuestionnaireRadio;
import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
import com.eternal.zjp.core.pojo.entity.TestRecord;
import com.eternal.zjp.core.pojo.vo.QuestionnaireRadioVO;
import com.eternal.zjp.core.pojo.vo.QuestionnaireWhetherVO;
import com.eternal.zjp.core.service.QuestionnaireWhetherService;
import com.eternal.zjp.core.service.TestRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 是否型问卷表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class QuestionnaireWhetherServiceImpl extends ServiceImpl<QuestionnaireWhetherMapper, QuestionnaireWhether> implements QuestionnaireWhetherService {

    @Resource
    private QuestionnaireWhetherMapper questionnaireWhetherMapper;

    @Resource
    private QuestionnaireRadioMapper questionnaireRadioMapper;

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Resource
    private TestRecordService testRecordService;

    @Override
    public void submit(QuestionnaireWhetherVO questionnaireWhetherVO) {
        // 将传递过来的单题信息保存
        QuestionnaireWhether questionnaireWhether = new QuestionnaireWhether();
        BeanUtils.copyProperties(questionnaireWhetherVO, questionnaireWhether);

//        questionnaireWhether.setQuestionnaireId(questionnaireWhetherVO.getQuestionnaireId());
//        questionnaireWhether.setQuestionNum(questionnaireWhetherVO.getQuestionNum());
//        questionnaireWhether.setQuestionTitle(questionnaireWhetherVO.getQuestionTitle());
//        questionnaireWhether.setTrueScore(questionnaireWhetherVO.getTrueScore());
//        questionnaireWhether.setFalseScore(questionnaireWhetherVO.getFalseScore());

        questionnaireWhetherMapper.insert(questionnaireWhether);

        // 更新问卷状态
        Questionnaire questionnaire = questionnaireMapper.selectById(questionnaireWhetherVO.getQuestionnaireId());
        questionnaire.setStatus(0);
        questionnaireMapper.updateById(questionnaire);

    }

    @Override
    public QuestionnaireWhetherVO getQuestionnaireDetails(Integer id) {
        QuestionnaireWhether questionnaireWhether = baseMapper.selectById(id);

        QuestionnaireWhetherVO questionnaireWhetherVO = new QuestionnaireWhetherVO();
        BeanUtils.copyProperties(questionnaireWhether, questionnaireWhetherVO);

        return questionnaireWhetherVO;
    }

    @Override
    public void radioSubmit(QuestionnaireRadioVO questionnaireRadioVO) {
        // 将传递过来的单题信息保存
        QuestionnaireRadio questionnaireRadio = new QuestionnaireRadio();
        BeanUtils.copyProperties(questionnaireRadioVO, questionnaireRadio);

//        questionnaireWhether.setQuestionnaireId(questionnaireWhetherVO.getQuestionnaireId());
//        questionnaireWhether.setQuestionNum(questionnaireWhetherVO.getQuestionNum());
//        questionnaireWhether.setQuestionTitle(questionnaireWhetherVO.getQuestionTitle());
//        questionnaireWhether.setTrueScore(questionnaireWhetherVO.getTrueScore());
//        questionnaireWhether.setFalseScore(questionnaireWhetherVO.getFalseScore());

        questionnaireRadioMapper.insert(questionnaireRadio);

        // 更新问卷状态
        Questionnaire questionnaire = questionnaireMapper.selectById(questionnaireRadioVO.getQuestionnaireId());
        questionnaire.setStatus(0);
        questionnaireMapper.updateById(questionnaire);
    }

    @Override
    public int calScore(Integer questionnaireId, Integer userId, ArrayList<Map<String, Integer>> radioList) {

        QueryWrapper<QuestionnaireWhether> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", questionnaireId).orderByAsc("question_num");
        List<QuestionnaireWhether> itemList = questionnaireWhetherMapper.selectList(queryWrapper);

        int score = 0;

        if(itemList.size() != radioList.size()) Assert.notNull(null, ResponseEnum.QUESTION_NULL_ERROR);



        for (Map<String, Integer> map : radioList) {
            int index = 0, value = 0;
            index = map.get("index");
            value = map.get("value");
//            System.out.println(index + ": " + value);

            if (value == 1) {
                score += itemList.get(index-1).getTrueScore();
            } else {
                score += itemList.get(index-1).getFalseScore();
            }
        }

        TestRecord testRecord = new TestRecord();
        testRecord.setUserId(userId);
        testRecord.setQuestionnaireId(questionnaireId);
        testRecord.setScore(score);
        testRecordService.save(testRecord);

        return score;
    }

}
