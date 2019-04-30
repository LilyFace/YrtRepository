package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 订单表现层
 * @Date: 2019/04/25 18:36
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/orders")
public class OrdersController {
    private OrdersService ordersService;

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    /**
     * @param
     * @description: 查询所有订单数据
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/25 18:38
     * @throws:
     **/
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "size", defaultValue = "4") Integer size) {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, size);
        //PageInfo就是一个分页Bean
        PageInfo pageInfo = new PageInfo<>(ordersList);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    /**
     * @param ordersId
     * @description: 订单详情
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/26 16:09
     * @throws:
     **/
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id") String ordersId) {
        ModelAndView mv = new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders", orders);
        mv.setViewName("orders-show");
        return mv;
    }
}
