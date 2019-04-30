package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 订单业务逻辑层
 * @Date: 2019/04/25 18:28
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface OrdersService {
    /**
     * @param
     * @param page
     * @param size
     * @description: 查询所有订单数据
     * @return: java.util.List<com.itheima.domain.Orders>
     * @author: YangRunTao
     * @date: 2019/04/25 18:29
     * @throws:
     **/
    List<Orders> findAll(int page, int size);

    /**
     * @param ordersId
     * @description: 根据id查询订单详情
     * @return: com.itheima.domain.Orders
     * @author: YangRunTao
     * @date: 2019/04/26 16:09
     * @throws:
     **/
    Orders findById(String ordersId);
}
