package com.zhanglinchun.sell.service.impl;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.zhanglinchun.sell.dto.OrderDTO;
import com.zhanglinchun.sell.service.PayService;

/**
 * 微信支付
 */
public class PayServiceImpl implements PayService {
    @Override
    public PayResponse create(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public PayResponse notify(String notifyData) {
        return null;
    }

    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        return null;
    }
}
