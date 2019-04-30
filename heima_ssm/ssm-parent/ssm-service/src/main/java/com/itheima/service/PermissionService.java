package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 权限业务层
 * @Date: 2019/04/27 9:48
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface PermissionService {
    /**
     * @param
     * @description: 查询所有权限
     * @return: java.util.List<com.itheima.domain.Permission>
     * @author: YangRunTao
     * @date: 2019/04/27 9:53
     * @throws:
     **/
    List<Permission> findAll();

    /**
     * @param permission
     * @description: 保存权限逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/27 9:54
     * @throws:
     **/
    void save(Permission permission);
}
