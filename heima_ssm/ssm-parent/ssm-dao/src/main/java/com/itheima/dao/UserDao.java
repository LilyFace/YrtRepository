package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


/**
 * @Author: YangRunTao
 * @Description: 用户持久层
 * @Date: 2019/04/26 18:22
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface UserDao {
    /**
     * @param username
     * @description: 根据用户名查询用户
     * @return: com.itheima.domain.UserInfo
     * @author: YangRunTao
     * @date: 2019/04/26 18:26
     * @throws:
     **/
    @Select("select * from Users where username = #{username}")
    @Results({
            @Result(id = true,
                    property = "id",
                    column = "id"),
            @Result(property = "roles",
                    column = "id",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId",
                            fetchType = FetchType.LAZY))})
    UserInfo findUserByUsername(String username);

    /**
     * @param
     * @description: 查询所有用户
     * @return: java.util.List<org.springframework.security.core.userdetails.User>
     * @author: YangRunTao
     * @date: 2019/04/26 20:26
     * @throws:
     **/
    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    /**
     * @param userInfo
     * @description: 保存用户
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:45
     * @throws:
     **/
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     * @param id
     * @description: 根据用户id查询数据(包含角色)
     * @return: com.itheima.domain.UserInfo
     * @author: YangRunTao
     * @date: 2019/04/27 13:14
     * @throws:
     **/
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roles",
                    column = "id",
                    javaType = java.util.List.class,
                    many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId",
                            fetchType = FetchType.LAZY))
    })
    UserInfo findById(String id) throws Exception;

    /**
     * @param userid
     * @description: 查询可以添加的角色
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/27 14:53
     * @throws:
     **/
    @Select("select * from role where id not in (select roleid from users_role where userid = #{userId})")
    List<Role> findCanAddRoles(String userid) throws Exception;

    /**
     * @param userId
     * @param roleId
     * @description: 添加角色给用户
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 9:22
     * @throws:
     **/
    @Insert("insert into USERS_ROLE(USERID,ROLEID) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;
}
