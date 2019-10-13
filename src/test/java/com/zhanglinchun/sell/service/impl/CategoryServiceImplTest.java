package com.zhanglinchun.sell.service.impl;

import com.zhanglinchun.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory product = categoryService.findOne(1);
        System.out.println(product);
        log.info("======= findOne =======");
        Assert.assertNotNull(product);
    }

    @Test
    public void findAll() {
        List<ProductCategory> productList = categoryService.findAll();
        System.out.println(productList);
        Assert.assertNotNull(productList);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1, 2);
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(list);
        System.out.println(productCategoryList);
        Assert.assertNotNull(productCategoryList);
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("必买好货", 1);
        ProductCategory category = categoryService.save(productCategory);
        log.info("======= {} =======","必买好货");
        Assert.assertNotNull(category);
    }
}