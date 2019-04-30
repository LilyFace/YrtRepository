package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 产品业务层
 * @Date: 2019/04/23 18:06
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface ProductService {

    /**
     * @param
     * @description: 查询所有产品
     * @return: java.util.List<com.itheima.domain.Product>
     * @author: YangRunTao
     * @date: 2019/04/23 18:09
     * @throws:
     **/
    List<Product> findAll();

    /**
     * @param
     * @description: 保存产品的业务逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/25 10:46
     * @throws:
     **/
    void save(Product product);
}
