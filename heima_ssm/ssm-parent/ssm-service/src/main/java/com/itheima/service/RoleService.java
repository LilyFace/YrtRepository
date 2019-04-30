package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 角色业务层
 * @Date: 2019/04/27 9:23
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface RoleService {
    /**
     * @param
     * @description: 查询所有角色
     * @return: java.util.List<org.springframework.security.core.userdetails.Role>
     * @author: YangRunTao
     * @date: 2019/04/26 20:25
     * @throws:
     **/
    List<Role> findAll();

    /**
     * @param role
     * @description: 保存角色逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:43
     * @throws:
     **/
    void save(Role role);

    /**
     * @param roleId
     * @description: 根据角色id查询角色信息
     * @return: com.itheima.domain.Role
     * @author: YangRunTao
     * @date: 2019/04/28 10:49
     * @throws:
     **/
    Role findRoleById(String roleId);

    /**
     * @param roleId
     * @description: 查询可以添加的权限
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/28 10:49
     * @throws:
     **/
    List<Permission> findCanAddRoles(String roleId);

    /**
     * @param roleId
     * @param permissionIds
     * @description: 给角色添加权限
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 11:18
     * @throws:
     **/
    void addPermissionToRole(String roleId, String[] permissionIds);
}
