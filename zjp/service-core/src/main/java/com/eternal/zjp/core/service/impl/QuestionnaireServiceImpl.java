package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.QuestionnaireMapper;
import com.eternal.zjp.core.mapper.QuestionnaireWhetherMapper;
import com.eternal.zjp.core.pojo.entity.Questionnaire;
import com.eternal.zjp.core.pojo.vo.QuestionnaireVO;
import com.eternal.zjp.core.service.QuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 问卷表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class QuestionnaireServiceImpl extends ServiceImpl<QuestionnaireMapper, Questionnaire> implements QuestionnaireService {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Resource
    private QuestionnaireWhetherMapper questionnaireWhetherMapper;

    @Override
    public QuestionnaireVO getQuestionnaireVOById(Integer id) {
        //获取信息
        Questionnaire questionnaire = questionnaireMapper.selectById(id);

        //填充基本信息
        QuestionnaireVO questionnaireVO = new QuestionnaireVO();
        BeanUtils.copyProperties(questionnaire, questionnaireVO);

        //名字
        questionnaireVO.setQuestionnaireName(questionnaire.getQuestionnaireName());

        //类型
//        questionnaireVO.setQuestionnaireType(questionnaire.getSex()==1?"男":"女");
        questionnaireVO.setQuestionnaireType(questionnaire.getQuestionnaireType());

//        //计算下拉列表选中内容
//        String education = dictService.getNameByParentDictCodeAndValue("education", questionnaire.getEducation());
//        String industry = dictService.getNameByParentDictCodeAndValue("moneyUse", questionnaire.getIndustry());
//        String income = dictService.getNameByParentDictCodeAndValue("income", questionnaire.getIncome());
//        String returnSource = dictService.getNameByParentDictCodeAndValue("returnSource", questionnaire.getReturnSource());
//        String contactsRelation = dictService.getNameByParentDictCodeAndValue("relation", questionnaire.getContactsRelation());
//
//        //设置下拉列表选中内容
//        questionnaireVO.setEducation(education);
//        questionnaireVO.setIndustry(industry);
//        questionnaireVO.setIncome(income);
//        questionnaireVO.setReturnSource(returnSource);
//        questionnaireVO.setContactsRelation(contactsRelation);
//
//        //审批状态
//        String status = BorrowerStatusEnum.getMsgByStatus(questionnaire.getStatus());
//        questionnaireVO.setStatus(status);
//
//        //获取附件VO列表
//        List<BorrowerAttachVO> borrowerAttachVOList =  borrowerAttachService.selectBorrowerAttachVOList(id);
//        questionnaireVO.setBorrowerAttachVOList(borrowerAttachVOList);

        return questionnaireVO;
    }

    @Override
    public IPage<Questionnaire> listPage(Page<Questionnaire> pageParam, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return baseMapper.selectPage(pageParam, null);
        }

        QueryWrapper<Questionnaire> questionnaireQueryWrapper = new QueryWrapper<>();
        questionnaireQueryWrapper
                .like("questionnaire_name", keyword)
                .or().like("questionnaire_type", keyword)
                .or().like("author_id", keyword)
                .orderByDesc("id");

        return baseMapper.selectPage(pageParam, questionnaireQueryWrapper);
    }

    @Override
    public int getQuestionItemNum(Integer id) {

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("questionnaire_id", id);

        return questionnaireWhetherMapper.selectList(queryWrapper).size();
    }
}
