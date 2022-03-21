package com.eternal.zjp.core.service;

import com.eternal.zjp.core.pojo.entity.QuestionnaireWhether;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.vo.QuestionnaireWhetherVO;

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
}
