package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.eternal.zjp.core.pojo.entity.TestRecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-04-08
 */
public interface TestRecordService extends IService<TestRecord> {

    IPage<TestRecord> listPage(Page<TestRecord> pageParam, String keyword);
}
