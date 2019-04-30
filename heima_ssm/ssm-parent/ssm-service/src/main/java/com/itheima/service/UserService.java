package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


/**
 * @Author: YangRunTao
 * @Description: 用户业务逻辑
 * @Date: 2019/04/26 18:19
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
public interface UserService extends UserDetailsService {

    /**
     * @param
     * @description: 查询所有用户
     * @return: java.util.List<org.springframework.security.core.userdetails.User>
     * @author: YangRunTao
     * @date: 2019/04/26 20:25
     * @throws:
     **/
    List<UserInfo> findAll();

    /**
     * @param userInfo
     * @description: 保存用户逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:43
     * @throws:
     **/
    void save(UserInfo userInfo);

    /**
     * @param id
     * @description: 根据用户id查询数据
     * @return: com.itheima.domain.UserInfo
     * @author: YangRunTao
     * @date: 2019/04/27 13:11
     * @throws:
     **/
    UserInfo findById(String id);

    /**
     * @param userid
     * @description: 查询可以添加的权限
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/27 14:44
     * @throws:
     **/
    List<Role> findCanAddRoles(String userid);

    /**
     * @param userId
     * @param roleIds
     * @description: 给用户添加角色
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 9:20
     * @throws:
     **/
    void addRoleToUser(String userId, String[] roleIds);
}
