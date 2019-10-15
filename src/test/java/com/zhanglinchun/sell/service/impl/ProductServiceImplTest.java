package com.zhanglinchun.sell.service.impl;

import com.zhanglinchun.sell.dataobject.ProductInfo;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo product = productService.findOne("1");
        log.info(product.toString());
        Assert.assertNotNull(product);
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productService.findUpAll();
        System.out.println(productInfoList);
        Assert.assertNotNull(productInfoList);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotNull(productInfoPage);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("2");
        productInfo.setProductName("小炒肉");
        productInfo.setProductPrice(new BigDecimal("20"));
        productInfo.setProductStock(100);
        productInfo.setProductStatus(0);
        productInfo.setProductDescription("青椒小炒肉，很辣的！");
        productInfo.setProductIcon("http://");
        productInfo.setCategoryType(2);
        ProductInfo product = productService.save(productInfo);
        Assert.assertNotNull(product);
    }
}