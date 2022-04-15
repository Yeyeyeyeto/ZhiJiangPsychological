package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.ConsultantAttachMapper;
import com.eternal.zjp.core.pojo.entity.ConsultantAttach;
import com.eternal.zjp.core.pojo.vo.ConsultantAttachVO;
import com.eternal.zjp.core.service.ConsultantAttachService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咨询师身份验证表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class ConsultantAttachServiceImpl extends ServiceImpl<ConsultantAttachMapper, ConsultantAttach> implements ConsultantAttachService {

    @Override
    public List<ConsultantAttachVO> selectConsultantAttachVOList(Integer id) {
        QueryWrapper<ConsultantAttach> consultantAttachQueryWrapper = new QueryWrapper<>();
        consultantAttachQueryWrapper.eq("consultant_id", id);
        List<ConsultantAttach> borrowerAttachList = baseMapper.selectList(consultantAttachQueryWrapper);

        List<ConsultantAttachVO> consultantAttachVOList = new ArrayList<>();
        borrowerAttachList.forEach(consultantAttach -> {
            ConsultantAttachVO consultantAttachVO = new ConsultantAttachVO();
            consultantAttachVO.setImageType(consultantAttach.getImageType());
            consultantAttachVO.setImageUrl(consultantAttach.getImageUrl());
            consultantAttachVOList.add(consultantAttachVO);
        });

        return consultantAttachVOList;
    }
}
