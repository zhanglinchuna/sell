package com.zhanglinchun.sell.service;

import com.zhanglinchun.sell.dataobject.ProductInfo;
import com.zhanglinchun.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);
    // 查询所有上架架商品列表
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);
    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);
    // 上架

    // 下架
}
