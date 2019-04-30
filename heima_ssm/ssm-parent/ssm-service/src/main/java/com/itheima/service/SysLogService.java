package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 日志信息业务层
 * @Date: 2019/04/28 20:16
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface SysLogService {
    /**
     * @param sysLog
     * @description: 保存日志信息
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/29 9:50
     * @throws:
     **/
    void save(SysLog sysLog);

    /**
     * @param
     * @description: 查询日志
     * @return: java.util.List<com.itheima.domain.SysLog>
     * @author: YangRunTao
     * @date: 2019/04/29 9:58
     * @throws:
     **/
    List<SysLog> findAll();
}
