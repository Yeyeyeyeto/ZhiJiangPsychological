package com.eternal.zjp.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.eternal.zjp.core.mapper.ConsultingOrderMapper;
import com.eternal.zjp.core.pojo.entity.ConsultingOrder;
import com.eternal.zjp.core.service.ConsultingOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-04-26
 */
@Service
public class ConsultingOrderServiceImpl extends ServiceImpl<ConsultingOrderMapper, ConsultingOrder> implements ConsultingOrderService {

    @Resource
    private ConsultingOrderService orderService;

    @Override
    public IPage<ConsultingOrder> listPage(Page<ConsultingOrder> pageParam, String keyword) {
        if (StringUtils.isBlank(keyword)) {
            return baseMapper.selectPage(pageParam, null);
        }

        QueryWrapper<ConsultingOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper
                .like("user_id", keyword)
                .or().like("consultant_id", keyword)
                .orderByDesc("id");

        return baseMapper.selectPage(pageParam, orderQueryWrapper);
    }

    @Override
    public boolean saveNewOrder(Integer consultantId, Integer userId, Integer number) {
        ConsultingOrder order = new ConsultingOrder();
        order.setConsultantId(consultantId);
        order.setUserId(userId);
        order.setAmount(number);
        order.setStatus(0);

        return orderService.save(order);
    }
}
