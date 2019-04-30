package com.itheima.service.impl;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 产品业务层
 * @Date: 2019/04/23 18:06
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("productService")
public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * @param
     * @description: 查询所有产品
     * @return: java.util.List<com.itheima.domain.Product>
     * @author: YangRunTao
     * @date: 2019/04/23 18:11
     * @throws:
     **/
    @Override
    public List<Product> findAll() {
        List<Product> list = null;
        try {
            list = productDao.findAll();
        } catch (Exception e) {
            //TODO(有时间补写一个自定义异常用于提示)
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param
     * @description: 保存产品的业务逻辑实现
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/25 10:47
     * @throws:
     **/
    @Override
    public void save(Product product) {
        try {
            productDao.save(product);
        } catch (Exception e) {
            //TODO(有时间补写一个自定义异常用于提示)
            e.printStackTrace();
        }
    }
}
