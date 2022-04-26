package com.eternal.zjp.core.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.common.result.R;
import com.eternal.zjp.base.util.JwtUtils;
import com.eternal.zjp.core.pojo.entity.ConsultingOrder;
import com.eternal.zjp.core.service.ConsultingOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Api(tags = "订单管理")
@RestController
@RequestMapping("/admin/core/order")
@Slf4j
public class AdminConsultingOrderController {

    @Resource
    private ConsultingOrderService consultingOrderService;

    @ApiOperation("订单列表")
    @GetMapping("/list")
    public R listAll() {
        List<ConsultingOrder> list = consultingOrderService.list();
        return R.ok().data("list", list);
    }

    @ApiOperation(value = "根据id删除数据记录", notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        boolean result = consultingOrderService.removeById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("新增订单")
    @PostMapping("/save")
    public R submit(@RequestBody Map<String, Object> maps, HttpServletRequest request) {
        Integer consultantId = (Integer) maps.get("key1");
        String number = (String) maps.get("key2");

        String token = request.getHeader("token");
        Integer userId = JwtUtils.getUserId(token);

        Integer number1 = Integer.parseInt(number);

        boolean result = consultingOrderService.saveNewOrder(consultantId, userId, number1);

        if (result) {
            return R.ok().message("添加成功");
        } else {
            return R.error().message("添加失败");
        }
    }

    @ApiOperation("根据id获取订单")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id", required = true, example = "1")
            @PathVariable Integer id) {
        ConsultingOrder order = consultingOrderService.getById(id);
        if (order != null) {
            return R.ok().data("record", order);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("更新订单")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "订单对象", required = true)
            @RequestBody ConsultingOrder order) {
        boolean result = consultingOrderService.updateById(order);
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
        Page<ConsultingOrder> pageParam = new Page<>(page, limit);
        IPage<ConsultingOrder> pageModel = consultingOrderService.listPage(pageParam, keyword);
        return R.ok().data("pageModel", pageModel);
    }

}

