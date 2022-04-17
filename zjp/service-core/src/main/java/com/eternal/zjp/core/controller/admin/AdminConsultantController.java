package com.eternal.zjp.core.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.result.R;
import com.eternal.zjp.core.pojo.entity.Consultant;
import com.eternal.zjp.core.pojo.query.ConsultantQuery;
import com.eternal.zjp.core.pojo.vo.ConsultantDetailVO;
import com.eternal.zjp.core.service.ConsultantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @ApiOperation("咨询师列表")
    @GetMapping("/list")
    public R listAll() {
        List<Consultant> list = consultantService.list();
        return R.ok().data("list", list);
    }

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

    @ApiOperation("获取咨询师信息")
    @GetMapping("/show/{id}")
    public R show(
            @ApiParam(value = "咨询师id", required = true)
            @PathVariable Integer id){

        ConsultantDetailVO consultantDetailVO = consultantService.getConsultantDetailVOById(id);
        return R.ok().data("consultantDetailVO", consultantDetailVO);
    }

    @ApiOperation("咨询资格审批")
    @PutMapping("/approval/{id}/{status}")
    public R approval(
            @ApiParam(value = "咨询师id", required = true)
            @PathVariable("id") Integer id,

            @ApiParam(value = "咨询师认证状态", required = true)
            @PathVariable("status") Integer status) {
        consultantService.approval(id, status);
        return R.ok().message("审批完成");
    }

    @ApiOperation("根据id获取咨询师")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        Consultant consultant = consultantService.getById(id);
        if (consultant != null) {
            return R.ok().data("record", consultant);
        } else {
            return R.error().message("数据不存在");
        }
    }

}
