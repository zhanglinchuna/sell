package com.zhanglinchun.sell.repository;

import com.zhanglinchun.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public  void save(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("2");
        orderMaster.setBuyerName("张林春");
        orderMaster.setBuyerPhone("13761166071");
        orderMaster.setBuyerOpenid("26011");
        orderMaster.setBuyerAddress("高宝路300弄19号101室");
        orderMaster.setOrderAmount(new BigDecimal("20"));
        OrderMaster order = repository.save(orderMaster);
        Assert.assertNotNull(order);
    }

    @Test
    public void findAll(){
        List<OrderMaster> orderMasters = repository.findAll();
        System.out.println(orderMasters);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<OrderMaster> byBuyerOpenid = repository.findByBuyerOpenid("26011", pageRequest);
        System.out.println(byBuyerOpenid.getTotalElements());
    }
}