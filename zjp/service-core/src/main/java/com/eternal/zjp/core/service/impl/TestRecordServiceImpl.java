package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.TestRecordMapper;
import com.eternal.zjp.core.pojo.entity.TestRecord;
import com.eternal.zjp.core.service.TestRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-04-08
 */
@Service
public class TestRecordServiceImpl extends ServiceImpl<TestRecordMapper, TestRecord> implements TestRecordService {

    @Override
    public IPage<TestRecord> listPage(Page<TestRecord> pageParam, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return baseMapper.selectPage(pageParam, null);
        }

        QueryWrapper<TestRecord> testRecordQueryWrapper = new QueryWrapper<>();
        testRecordQueryWrapper
                .like("user_id", keyword)
                .or().like("questionnaire_id", keyword)
                .or().like("score", keyword)
                .orderByDesc("id");

        return baseMapper.selectPage(pageParam, testRecordQueryWrapper);
    }


}
