package com.eternal.zjp.core.service.impl;

import com.eternal.zjp.core.pojo.entity.Order;
import com.eternal.zjp.core.mapper.OrderMapper;
import com.eternal.zjp.core.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Eternal
 * @since 2022-03-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
