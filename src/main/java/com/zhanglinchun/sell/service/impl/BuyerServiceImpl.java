package com.zhanglinchun.sell.service.impl;

import com.zhanglinchun.sell.dto.OrderDTO;
import com.zhanglinchun.sell.enums.OrderStatusEnum;
import com.zhanglinchun.sell.enums.ResultEnum;
import com.zhanglinchun.sell.exception.SellException;
import com.zhanglinchun.sell.service.BuyerService;
import com.zhanglinchun.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 查询订单openid是否一致
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openId, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openId, orderId);
        if (null == orderDTO) {
            log.error("【取消订单】查询不到订单！orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openId, String orderId) {
        if (StringUtils.isEmpty(openId)) {
            log.error("【查询订单】openid为空！");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO) {
            return null;
        }
        if (!openId.equalsIgnoreCase(orderDTO.getBuyerOpenid())) {
            log.error("【查询订单】订单的openid不一致！openId={},orderDTO={}", openId, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
