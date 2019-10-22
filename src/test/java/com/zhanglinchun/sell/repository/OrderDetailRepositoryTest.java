package com.zhanglinchun.sell.repository;

import com.zhanglinchun.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("3");
        orderDetail.setDetailId("2");
        orderDetail.setOrderId("2");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductName("干锅包菜");
        orderDetail.setProductPrice(new BigDecimal("18"));
        orderDetail.setProductQuantity(1);
        OrderDetail orderDetail1 = repository.save(orderDetail);
        Assert.assertNotNull(orderDetail1);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetails = repository.findByOrderId("1");
        System.out.println(orderDetails);
        Assert.assertNotNull(orderDetails);
    }
}