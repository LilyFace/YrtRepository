package com.itheima.dao;

import com.itheima.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 旅行者持久层
 * @Date: 2019/04/26 16:37
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface TravellerDao {
    /**
     * @param id
     * @description: 根据订单表查询旅行者
     * @return: java.util.List<com.itheima.domain.Traveller>
     * @author: YangRunTao
     * @date: 2019/04/26 16:49
     * @throws:
     **/
    @Select("select * from TRAVELLER where id in(select TRAVELLERID from ORDER_TRAVELLER where ORDERID = #{id})")
    List<Traveller> findByOrdersId(String id);
}
