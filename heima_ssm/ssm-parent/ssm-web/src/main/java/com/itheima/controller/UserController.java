package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 用户视图层
 * @Date: 2019/04/26 20:17
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @param
     * @description: 查询所有用户
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/26 20:23
     * @throws:
     **/
    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == 'yrt1'")
    public ModelAndView findAll() {
        ModelAndView mv =new ModelAndView();
        List<UserInfo> list = userService.findAll();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * @param
     * @description: 保存用户
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:40
     * @throws:
     **/
    @RequestMapping("/save.do")
    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == 'yrt1'")
    public String save(UserInfo userInfo) {
        try {
            userService.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }

    /**
     * @param id
     * @description: 查询用户详情
     * @return: ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/27 13:10
     * @throws:
     **/
    //查询指定id的用户
    @RequestMapping("/findById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == 'yrt1'")
    public ModelAndView findById(String id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * @param userid
     * @description: 查询用户以及用户可以添加的角色
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/27 16:08
     * @throws:
     **/
    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == 'yrt1'")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String userid) {
        ModelAndView mv = new ModelAndView();
        //1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        //2.根据用户id查询可以添加的角色
        List<Role> canAddRoles = userService.findCanAddRoles(userid);
        mv.addObject("user", userInfo);
        mv.addObject("roleList", canAddRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * @param userId
     * @param roleIds
     * @description: 给用户添加角色
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/27 16:09
     * @throws:
     **/
    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.principal.username == 'yrt1'")
    public String addRoleToUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "ids") String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
}
