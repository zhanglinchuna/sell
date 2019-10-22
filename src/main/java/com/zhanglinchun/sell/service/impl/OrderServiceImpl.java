package com.zhanglinchun.sell.service.impl;

import com.zhanglinchun.sell.dataobject.OrderDetail;
import com.zhanglinchun.sell.dataobject.OrderMaster;
import com.zhanglinchun.sell.dataobject.ProductInfo;
import com.zhanglinchun.sell.dto.CartDTO;
import com.zhanglinchun.sell.dto.OrderDTO;
import com.zhanglinchun.sell.enums.ResultEnum;
import com.zhanglinchun.sell.exception.SellException;
import com.zhanglinchun.sell.repository.OrderDetailRepository;
import com.zhanglinchun.sell.repository.OrderMasterRepository;
import com.zhanglinchun.sell.service.OrderService;
import com.zhanglinchun.sell.service.ProductService;
import com.zhanglinchun.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单服务
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductService productService;

    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        // 订单id
        String orderId = KeyUtil.genUniqueKey();
        // 订单商品总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        // 1.查询订单中的商品（库存，价格）
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();//获取订单中的商品列表
        for (OrderDetail orderDetail:orderDetailList){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());//查询订单中的商品明细
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2.计算总价（商品单价 * 商品数量 + 订单商品总价）
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            // 3.订单详情写入数据库（orderDetail表）
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(productInfo,orderDetail);//将productInfo的属性值拷贝给orderDetail的属性值
            orderDetailRepository.save(orderDetail);
        }
        // 4.订单写入数据库（orderMaster表）
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO,orderMaster);// 对象属性拷贝方法
        orderMasterRepository.save(orderMaster);
        // 5.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(
                e -> new CartDTO(e.getProductId(),e.getProductQuantity())
        ).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
