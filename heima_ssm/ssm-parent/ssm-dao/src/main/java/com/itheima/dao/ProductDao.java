package com.itheima.dao;

import com.itheima.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 产品持久层
 * @Date: 2019/04/23 16:19
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface ProductDao {
    /**
     * @param
     * @description: 查询所有产品数据
     * @return: java.util.List<com.itheima.domain.Product>
     * @author: YangRunTao
     * @date: 2019/04/23 16:19
     * @throws:
     **/
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * @param product
     * @description: 保存产品
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/25 10:42
     * @throws:
     **/
    @Insert("insert into " +
            "product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

    /**
     * @param id
     * @description: 根据id查询产品数据
     * @return:
     * @author: YangRunTao
     * @date: 2019/04/25 17:56
     * @throws:
     **/
    @Select("select * from product where id = #{id}")
    Product findById(String id);
}
