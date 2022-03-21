package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.QuestionnaireMapper;
import com.eternal.zjp.core.mapper.QuestionnaireWhetherMapper;
import com.eternal.zjp.core.pojo.entity.Questionnaire;
import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
import com.eternal.zjp.core.pojo.vo.QuestionnaireWhetherVO;
import com.eternal.zjp.core.service.QuestionnaireWhetherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    private QuestionnaireMapper questionnaireMapper;

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

}
