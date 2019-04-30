package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 用户业务逻辑
 * @Date: 2019/04/26 18:21
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @param username
     * @description: 重写安全框架的loadUserByUsername方法
     * @return: org.springframework.security.core.userdetails.UserDetails
     * @author: YangRunTao
     * @date: 2019/04/26 18:21
     * @throws:
     **/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户和他所拥有的权限
        UserInfo userInfo = userDao.findUserByUsername(username);
        //获得权限
        List<Role> roles = userInfo.getRoles();
        //赋予权限给框架知道用户所拥有的权限
        List<SimpleGrantedAuthority> authority = this.getAuthority(roles);
        //返回框架提供的User对象
        return new User(userInfo.getUsername(),
                userInfo.getPassword(),
                userInfo.getStatus() != 0,
                true,
                true,
                true,
                authority);
    }

    /**
     * @param roles 数据库查到的权限
     * @description: 在框架中添加并返回当前用户的权限
     * @return: java.util.List<org.springframework.security.core.authority.SimpleGrantedAuthority>
     * @author: YangRunTao
     * @date: 2019/04/26 18:53
     * @throws:
     **/
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        System.out.println(authorities);
        return authorities;
    }

    /**
     * @param
     * @description: 查询所有用户
     * @return: java.util.List<org.springframework.security.core.userdetails.User>
     * @author: YangRunTao
     * @date: 2019/04/26 20:25
     * @throws:
     **/
    @Override
    public List<UserInfo> findAll() {
        List<UserInfo> list = null;
        try {
            list = userDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * @param userInfo
     * @description: 保存用户逻辑
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:43
     * @throws:
     **/
    @Override
    public void save(UserInfo userInfo) {
        try {
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userDao.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param id
     * @description: 根据用户id查询数据
     * @return: com.itheima.domain.UserInfo
     * @author: YangRunTao
     * @date: 2019/04/27 13:12
     * @throws:
     **/
    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    /**
     * @param userid
     * @description: 查询可以添加的角色
     * @return: java.util.List<com.itheima.domain.Role>
     * @author: YangRunTao
     * @date: 2019/04/27 14:45
     * @throws:
     **/
    @Override
    public List<Role> findCanAddRoles(String userid) {
        List<Role> list = null;
        try {
            list = userDao.findCanAddRoles(userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param userId
     * @param roleIds
     * @description: 给用户添加角色
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/28 9:21
     * @throws:
     **/
    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        if (StringUtils.isNotBlank(userId) && roleIds != null && roleIds.length > 0) {
            for (String roleId : roleIds) {
                try {
                    userDao.addRoleToUser(userId, roleId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            //TODO
            throw new RuntimeException("非法操作");
        }
    }
}
