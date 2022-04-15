package com.eternal.zjp.core.service;

import com.eternal.zjp.core.pojo.entity.ConsultantAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.vo.ConsultantAttachVO;

import java.util.List;

/**
 * <p>
 * 咨询师身份验证表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
public interface ConsultantAttachService extends IService<ConsultantAttach> {

    List<ConsultantAttachVO> selectConsultantAttachVOList(Integer id);
}
