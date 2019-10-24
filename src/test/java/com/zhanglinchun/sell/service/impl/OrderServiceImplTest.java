package com.zhanglinchun.sell.service.impl;

import com.zhanglinchun.sell.dataobject.OrderDetail;
import com.zhanglinchun.sell.dto.OrderDTO;
import com.zhanglinchun.sell.enums.OrderStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张林春");
        orderDTO.setBuyerAddress("金海路1000号金领之都27号楼8层");
        orderDTO.setBuyerPhone("13761166071");
        orderDTO.setBuyerOpenid("26011");

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("2");
        orderDetail.setProductQuantity(1);
        orderDetailList.add(orderDetail);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO order = orderService.create(orderDTO);
        System.out.println(order);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1571924283955309004");
        System.out.println(orderDTO);
        orderDTO.getOrderDetailList().forEach(System.out::println);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderDTO> list = orderService.findList("26011", pageRequest);
        list.forEach(System.out::println);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1571924283955309004");
        OrderDTO order = orderService.cancel(orderDTO);
        System.out.println(order);
        Assert.assertNotNull(order);
    }
}