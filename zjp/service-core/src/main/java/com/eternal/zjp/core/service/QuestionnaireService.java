package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.zjp.core.pojo.entity.Questionnaire;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.vo.QuestionnaireVO;

/**
 * <p>
 * 问卷表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
public interface QuestionnaireService extends IService<Questionnaire> {

    QuestionnaireVO getQuestionnaireVOById(Integer id);

    IPage<Questionnaire> listPage(Page<Questionnaire> pageParam, String keyword);
}
