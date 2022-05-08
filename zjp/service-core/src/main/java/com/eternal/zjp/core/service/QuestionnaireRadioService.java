package com.eternal.zjp.core.service;

import com.eternal.zjp.core.pojo.entity.QuestionnaireRadio;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
public interface QuestionnaireRadioService extends IService<QuestionnaireRadio> {

    int calScore(Integer questionnaireId, Integer userId, ArrayList<Map<String, Integer>> radioList);

}
