package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.result.R;
import com.eternal.zjp.core.pojo.entity.User;
import com.eternal.zjp.core.pojo.query.UserQuery;
import com.eternal.zjp.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther Eternal
 * @Date 2022/3/9
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin/core/user")
@Slf4j
public class AdminUserController {

    @Resource
    private UserService userService;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @PathVariable(value = "查询对象", required = false)
                    UserQuery userQuery
    ) {
        Page<User> pageParam = new Page<>(page, limit);
        IPage<User> pageModel = userService.listPage(pageParam, userQuery);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("锁定和解锁")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable("id") Integer id,

            @ApiParam(value = "锁定状态（0：锁定  1：正常）", required = true)
            @PathVariable("status") Integer status) {
        userService.lock(id, status);
        return R.ok().message(status == 1 ? "解锁成功": "锁定成功");
    }

}
