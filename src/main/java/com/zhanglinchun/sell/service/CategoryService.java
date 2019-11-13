package com.zhanglinchun.sell.service;

import com.zhanglinchun.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 商品类目
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);
}
