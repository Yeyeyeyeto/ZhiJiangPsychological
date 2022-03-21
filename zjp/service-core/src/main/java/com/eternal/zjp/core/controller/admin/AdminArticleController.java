package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.exception.BusinessException;
import com.eternal.common.result.R;
import com.eternal.common.result.ResponseEnum;
import com.eternal.zjp.core.pojo.entity.Article;
import com.eternal.zjp.core.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther Eternal
 * @Date 2022/3/21
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/admin/core/article")
@Slf4j
public class AdminArticleController {

    @Resource
    private ArticleService articleService;

    @ApiOperation("文章列表")
    @GetMapping("/list")
    public R listAll() {
        List<Article> list = articleService.list();
        return R.ok().data("list", list);
    }

    @ApiOperation(value = "根据id删除数据记录", notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        boolean result = articleService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增文章")
    @PutMapping("/save")
    public R save(
            @ApiParam(value = "文章对象", required = true)
            @RequestBody Article article) {
        // 如果为空抛出自定义异常
        if (article.getArticleName() == null) {
            throw new BusinessException(ResponseEnum.ARTICLE_AMOUNT_NULL_ERROR);
        }
        boolean result = articleService.save(article);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id获取文章")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        Article article = articleService.getById(id);
        if (article != null) {
            return R.ok().data("record", article);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新文章")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "文章对象", required = true)
            @RequestBody Article article) {
        boolean result = articleService.updateById(article);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("获取文章分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(value = "查询关键字", required = false)
            @RequestParam String keyword) {
        //这里的@RequestParam其实是可以省略的，但是在目前的swagger版本中（2.9.2）不能省略，
        //否则默认将没有注解的参数解析为body中的传递的数据

        Page<Article> pageParam = new Page<>(page, limit);
        IPage<Article> pageModel = articleService.listPage(pageParam, keyword);
        return R.ok().data("pageModel", pageModel);
    }


}
