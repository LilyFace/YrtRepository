package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description:
 * @Date: 2019/04/27 9:23
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * @param
     * @description: 查询所有角色
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/27 9:35
     * @throws:
     **/
    @Override
    public List<Role> findAll() {
        List<Role> list = null;
        try {
            list = roleDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param role
     * @description: 保存角色逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/27 9:35
     * @throws:
     **/
    @Override
    public void save(Role role) {
        try {
            roleDao.save(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param roleId
     * @description: 根据角色id查询角色信息
     * @return: com.itheima.domain.Role
     * @author: YangRunTao
     * @date: 2019/04/28 10:50
     * @throws:
     **/
    @Override
    public Role findRoleById(String roleId) {
        Role role = null;
        try {
            role = roleDao.findRoleById(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * @param roleId
     * @description: 查询可以添加的权限
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/28 10:50
     * @throws:
     **/
    @Override
    public List<Permission> findCanAddRoles(String roleId) {
        List<Permission> list = null;
        try {
            list = roleDao.findCanAddRoles(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param roleId
     * @param permissionIds
     * @description: 给角色添加权限
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 11:20
     * @throws:
     **/
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        try {
            for (String permissionId : permissionIds) {
                roleDao.addPermissionToRole(roleId,permissionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
