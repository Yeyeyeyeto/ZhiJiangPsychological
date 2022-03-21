package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.zjp.core.pojo.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-21
 */
public interface ArticleService extends IService<Article> {

    IPage<Article> listPage(Page<Article> pageParam, String keyword);
}
