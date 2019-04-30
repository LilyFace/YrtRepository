package com.itheima.service.impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 日志信息业务层
 * @Date: 2019/04/28 20:18
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    private SysLogDao sysLogDao;

    @Autowired
    public void setSysLogDao(SysLogDao sysLogDao) {
        this.sysLogDao = sysLogDao;
    }

    /**
     * @param sysLog
     * @description: 保存日志信息
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/29 9:51
     * @throws:
     **/
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    /**
     * @param
     * @description: 查询日志
     * @return: java.util.List<com.itheima.domain.SysLog>
     * @author: YangRunTao
     * @date: 2019/04/29 9:59
     * @throws:
     **/
    @Override
    public List<SysLog> findAll() {
        List<SysLog> list = null;
        try {
            list = sysLogDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
