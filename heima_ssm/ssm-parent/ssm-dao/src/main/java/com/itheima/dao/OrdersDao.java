package com.itheima.dao;

import com.itheima.domain.Member;
import com.itheima.domain.Orders;
import com.itheima.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 订单持久层
 * @Date: 2019/04/25 17:46
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface OrdersDao {

    /**
     * @param
     * @param page
     * @param size
     * @description: 查询订单详情
     * @return: java.util.List<com.itheima.domain.Orders>
     * @author: YangRunTao
     * @date: 2019/04/25 17:47
     * @throws:
     **/
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product",
                    column = "productId",
                    javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById", fetchType = FetchType.LAZY))}
    )
    List<Orders> findAll(int page, int size) throws Exception;

    /**
     * @param ordersId
     * @description: 根据id查询订单详情
     * @return: com.itheima.domain.Orders
     * @author: YangRunTao
     * @date: 2019/04/26 16:11
     * @throws:
     **/
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "product",
                    column = "PRODUCTID",
                    javaType = Product.class,
                    one = @One(select = "com.itheima.dao.ProductDao.findById",
                            fetchType = FetchType.LAZY)
            ),
            @Result(property = "travellers",
                    column = "id",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.TravellerDao.findByOrdersId",
                            fetchType = FetchType.LAZY)
            ),
            @Result(property = "member",
                    column = "memberId",
                    javaType = Member.class,
                    one = @One(select = "com.itheima.dao.MemberDao.findById",
                            fetchType = FetchType.LAZY))}
    )
    Orders findById(String ordersId) throws Exception;
}
