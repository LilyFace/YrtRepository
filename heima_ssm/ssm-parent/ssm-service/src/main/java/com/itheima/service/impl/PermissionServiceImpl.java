package com.itheima.service.impl;

import com.itheima.dao.PermissionDao;
import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 权限业务层
 * @Date: 2019/04/27 9:48
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    private PermissionDao permissionDao;

    @Autowired
    public void setPermissionDao(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    /**
     * @param
     * @description: 查询所有权限
     * @return: java.util.List<com.itheima.domain.Permission>
     * @author: YangRunTao
     * @date: 2019/04/27 9:57
     * @throws:
     **/
    @Override
    public List<Permission> findAll() {
        List<Permission> list = null;
        try {
            list = permissionDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param permission
     * @description: 保存权限逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:43
     * @throws:
     **/
    @Override
    public void save(Permission permission) {
        try {
            permissionDao.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
