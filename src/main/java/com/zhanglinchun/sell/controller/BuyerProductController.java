package com.zhanglinchun.sell.controller;

import com.zhanglinchun.sell.dataobject.ProductCategory;
import com.zhanglinchun.sell.dataobject.ProductInfo;
import com.zhanglinchun.sell.service.impl.CategoryServiceImpl;
import com.zhanglinchun.sell.service.impl.ProductServiceImpl;
import com.zhanglinchun.sell.utils.ResultVOUtil;
import com.zhanglinchun.sell.vo.ProductInfoVO;
import com.zhanglinchun.sell.vo.ProductVO;
import com.zhanglinchun.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @RequestMapping("/list")
    public ResultVO list() {
        // 查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 查询商品所属类目
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        // 商品所属类目
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 拼装数据
        ArrayList<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory ProductCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            // 类目名称
            productVO.setCategoryName(ProductCategory.getCategoryName());
            // 类目code
            productVO.setCategoryType(ProductCategory.getCategoryType());

            ArrayList<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(ProductCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    // 把productInfo对象里的数据copy到productInfoVO对象里
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
