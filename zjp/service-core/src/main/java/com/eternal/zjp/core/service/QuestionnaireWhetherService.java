package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
import com.eternal.zjp.core.pojo.vo.QuestionnaireRadioVO;
import com.eternal.zjp.core.pojo.vo.QuestionnaireWhetherVO;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 * 是否型问卷表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
public interface QuestionnaireWhetherService extends IService<QuestionnaireWhether> {

    void submit(QuestionnaireWhetherVO questionnaireWhetherVO);

    QuestionnaireWhetherVO getQuestionnaireDetails(Integer id);

    void radioSubmit(QuestionnaireRadioVO questionnaireRadioVO);


    int calScore(Integer questionnaireId, Integer userId, ArrayList<Map<String, Integer>> radioList);
}
