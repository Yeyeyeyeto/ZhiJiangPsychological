package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.ArticleMapper;
import com.eternal.zjp.core.pojo.entity.Article;
import com.eternal.zjp.core.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public IPage<Article> listPage(Page<Article> pageParam, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return baseMapper.selectPage(pageParam, null);
        }

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper
                .like("article_name", keyword)
                .or().like("author_id", keyword)
                .orderByDesc("id");

        return baseMapper.selectPage(pageParam, articleQueryWrapper);
    }
}
