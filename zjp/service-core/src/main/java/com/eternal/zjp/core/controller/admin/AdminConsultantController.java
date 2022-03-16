package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.result.R;
import com.eternal.zjp.core.pojo.entity.Consultant;
import com.eternal.zjp.core.pojo.query.ConsultantQuery;
import com.eternal.zjp.core.service.ConsultantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther Eternal
 * @Date 2022/3/14
 */
@Api(tags = "咨询师管理")
@RestController
@RequestMapping("/admin/core/consultant")
@Slf4j
public class AdminConsultantController {

    @Resource
    private ConsultantService consultantService;

    @ApiOperation("获取咨询师分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @PathVariable(value = "查询对象", required = false)
                    ConsultantQuery consultantQuery
    ) {
        Page<Consultant> pageParam = new Page<>(page, limit);
        IPage<Consultant> pageModel = consultantService.listPage(pageParam, consultantQuery);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("锁定和解锁")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "咨询师id", required = true)
            @PathVariable("id") Integer id,

            @ApiParam(value = "锁定状态（0：锁定  1：正常）", required = true)
            @PathVariable("status") Integer status) {
        consultantService.lock(id, status);
        return R.ok().message(status == 1 ? "解锁成功": "锁定成功");
    }

}
