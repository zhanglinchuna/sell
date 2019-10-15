package com.zhanglinchun.sell.repository;

import com.zhanglinchun.sell.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfo = repository.findByProductStatus(1);

    }

    @Test
    public void save(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1");
        productInfo.setProductName("小米粥");
        productInfo.setProductPrice(new BigDecimal("10"));
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("小米粥很好喝呀！");
        productInfo.setProductIcon("http://");
        productInfo.setCategoryType(2);
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findAll(){
        List<ProductInfo> productInfoList = repository.findAll();
        log.info(productInfoList.listIterator().next().toString());
        System.out.println(productInfoList);
        Assert.assertNotNull(productInfoList);
    }
}