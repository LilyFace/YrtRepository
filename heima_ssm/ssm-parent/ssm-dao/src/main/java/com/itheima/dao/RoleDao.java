package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 角色持久层
 * @Date: 2019/04/26 18:32
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface RoleDao {
    /**
     * @param userId
     * @description: 根据用户id查询角色(包含具体权限)
     * @return:
     * @author: YangRunTao
     * @date: 2019/04/26 18:33
     * @throws:
     **/
    @SuppressWarnings("unused")
    @Select("select * from role where id in (select roleid from users_role where userid = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "permissions",
                    column = "id",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionsById",
                            fetchType = FetchType.LAZY))})
    List<Role> findRoleByUserId(String userId);

    /**
     * @param
     * @description: 查询所有角色
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/27 9:38
     * @throws:
     **/
    @Select("select * from role")
    List<Role> findAll();

    /**
     * @param role
     * @description: 保存角色
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/27 9:38
     * @throws:
     **/
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * @param roleId
     * @description: 根据角色id查询角色信息
     * @return: com.itheima.domain.Role
     * @author: YangRunTao
     * @date: 2019/04/28 10:53
     * @throws:
     **/
    @Select("select * from role where id = #{roleId}")
    Role findRoleById(String roleId) throws Exception;

    /**
     * @param roleId
     * @description: 查询可以添加的权限
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/28 10:53
     * @throws:
     **/
    @Select("select * from PERMISSION where id not in (select PERMISSIONID from ROLE_PERMISSION where roleid = #{roleId})")
    List<Permission> findCanAddRoles(String roleId) throws Exception;

    /**
     * @param
     * @param roleId
     * @param permissionId
     * @description: 给角色添加权限
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 11:22
     * @throws:
     **/
    @Insert("insert into ROLE_PERMISSION(PERMISSIONID,ROLEID) values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;
}
