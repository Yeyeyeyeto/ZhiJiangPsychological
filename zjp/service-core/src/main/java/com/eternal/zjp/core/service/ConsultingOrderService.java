package com.eternal.zjp.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eternal.zjp.core.pojo.entity.ConsultingOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Eternal
 * @since 2022-04-26
 */
public interface ConsultingOrderService extends IService<ConsultingOrder> {

    IPage<ConsultingOrder> listPage(Page<ConsultingOrder> pageParam, String keyword);

    boolean saveNewOrder(Integer consultantId, Integer userId, Integer number);
}
