package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.result.R;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.pojo.entity.TestRecord;
import com.eternal.zjp.core.service.TestRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther Eternal
 * @Date 2022/4/8
 */
@Api(tags = "测试记录管理")
@RestController
@RequestMapping("/admin/core/test")
@Slf4j
public class AdminTestRecordController {

    @Resource
    private TestRecordService testRecordService;

    @ApiOperation("问卷列表")
    @GetMapping("/list")
    public R listAll() {
        List<TestRecord> list = testRecordService.list();
        return R.ok().data("list", list);
    }

    @ApiOperation("获取问卷分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(value = "查询关键字", required = false)
            @RequestParam String keyword) {
        Page<TestRecord> pageParam = new Page<>(page, limit);
        IPage<TestRecord> pageModel = testRecordService.listPage(pageParam, keyword);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("用户测试问卷列表")
    @GetMapping("/userList")
    public R userList(HttpServletRequest request) {
        Integer userId = JwtUtils.getUserId(request.getHeader("token"));
        QueryWrapper<TestRecord> testRecordQueryWrapper = new QueryWrapper<>();
        testRecordQueryWrapper.eq("user_id", userId);
        List<TestRecord> list = testRecordService.list(testRecordQueryWrapper);
        return R.ok().data("list", list);
    }

}
