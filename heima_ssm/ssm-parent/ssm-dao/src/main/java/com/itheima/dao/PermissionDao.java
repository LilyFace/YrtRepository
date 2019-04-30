package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 权限持久层
 * @Date: 2019/04/27 9:48
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface PermissionDao {
    /**
     * @param
     * @description: 查询所有权限
     * @return: java.util.List<com.itheima.domain.Permission>
     * @author: YangRunTao
     * @date: 2019/04/27 9:57
     * @throws:
     **/
    @Select("select * from permission")
    List<Permission> findAll();

    /**
     * @param permission
     * @description: 保存权限
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/27 9:58
     * @throws:
     **/
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    /**
     * @param roleId
     * @description: 根据角色查询权限
     * @return: java.util.List<com.itheima.domain.Permission>
     * @author: YangRunTao
     * @date: 2019/04/27 13:49
     * @throws:
     **/
    @Select("select * from permission where id in (select permissionId from ROLE_PERMISSION where ROLEID = #{roleId})")
    List<Permission> findPermissionsById(String roleId);
}
