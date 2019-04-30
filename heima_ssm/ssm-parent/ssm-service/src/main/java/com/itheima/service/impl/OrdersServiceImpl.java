package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import com.itheima.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 订单业务逻辑层
 * @Date: 2019/04/25 18:30
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("ordersService")
public class OrdersServiceImpl implements OrdersService {
    private OrdersDao ordersDao;

    @Autowired
    public void setOrdersDao(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    /**
     * @param
     * @param page
     * @param size
     * @description: 查询所有订单数据
     * @return: java.util.List<com.itheima.domain.Orders>
     * @author: YangRunTao
     * @date: 2019/04/25 18:32
     * @throws:
     **/
    @Override
    public List<Orders> findAll(int page, int size) {
        List<Orders> list = null;
        try {
            //参数pageNum 是页码值   参数pageSize 代表是每页显示条数
            PageHelper.startPage(page, size);
            list = ordersDao.findAll(page, size);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param ordersId
     * @description: 根据id查询订单详情
     * @return: com.itheima.domain.Orders
     * @author: YangRunTao
     * @date: 2019/04/26 16:10
     * @throws:
     **/
    @Override
    public Orders findById(String ordersId) {
        Orders byId = null;
        try {
            byId = ordersDao.findById(ordersId);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
        return byId;
    }
}
