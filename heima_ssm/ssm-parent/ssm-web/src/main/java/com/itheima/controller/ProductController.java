package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 产品表现层
 * @Date: 2019/04/23 20:23
 * @Modified By:
 */
@SuppressWarnings({"JavaDoc"})
@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @param
     * @description: 查询全部产品
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/25 10:51
     * @throws:
     **/
    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList", ps);
        mv.setViewName("product-list");
        return mv;
    }

    /**
     * @param product
     * @description: 保存产品
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/25 10:53
     * @throws:
     **/
    @RequestMapping("/save.do")
    public String save(Product product) {
        productService.save(product);
        return "redirect:findAll.do";
    }

}
