package com.zhanglinchun.sell.controller;

import com.zhanglinchun.sell.dataobject.ProductCategory;
import com.zhanglinchun.sell.form.CategoryForm;
import com.zhanglinchun.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {
    
    @Autowired
    private CategoryService categoryService;

    /**
     * 商品类目列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(Map<String,Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("category/list",map);
    }

    /**
     * 商品类目详情
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        ProductCategory category = new ProductCategory();
        if (null != categoryId) {
            category = categoryService.findOne(categoryId);
        }
        map.put("category",category);
        return new ModelAndView("category/index", map);
    }

    /**
     * 修改/保存商品类目
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult, Map<String, Object> map){
        if (bindingResult.hasErrors()) {
            map.put("url", "/sell/seller/category/list");
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            return new ModelAndView("common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();
        if (null != categoryForm.getCategoryId()) {
            productCategory = categoryService.findOne(categoryForm.getCategoryId());
        }
        BeanUtils.copyProperties(categoryForm,productCategory);
        categoryService.save(productCategory);
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("common/success",map);
    }
}
