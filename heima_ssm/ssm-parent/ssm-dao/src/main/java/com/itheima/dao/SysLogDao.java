package com.itheima.dao;

import com.itheima.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 日志信息持久层
 * @Date: 2019/04/29 9:53
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface SysLogDao {
    /**
     * @param
     * @param sysLog
     * @description: 保存日志信息
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/29 9:53
     * @throws:
     **/
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    /**
     * @param
     * @description: 查询日志
     * @return: java.util.List<com.itheima.domain.SysLog>
     * @author: YangRunTao
     * @date: 2019/04/29 10:00
     * @throws:
     **/
    @Select("select * from sysLog order by visitTime desc")
    List<SysLog> findAll() throws Exception;
}
