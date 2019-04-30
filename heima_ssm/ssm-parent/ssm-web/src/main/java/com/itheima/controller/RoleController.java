package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 角色控制层
 * @Date: 2019/04/27 9:22
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * @param model
     * @description: 查询所有角色
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/26 20:23
     * @throws:
     **/
    @RequestMapping("/findAll.do")
    public String findAll(Model model) {
        List<Role> list = null;
        try {
            list = roleService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("roleList", list);
        return "role-list";
    }

    /**
     * @param
     * @description: 保存角色
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:40
     * @throws:
     **/
    @RequestMapping("/save.do")
    public String save(Role role) {
        try {
            roleService.save(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }

    /**
     * @param roleId
     * @description: 根据roleId查询role，并查询出可以添加的权限
     * @return: org.springframework.web.servlet.ModelAndView
     * @author: YangRunTao
     * @date: 2019/04/28 10:06
     * @throws:
     **/
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findRoleById(roleId);
        List<Permission> canAddRoles = roleService.findCanAddRoles(roleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", canAddRoles);
        mv.setViewName("role-permission-add");
        return mv;
    }

    /**
     * @param roleId
     * @param permissionIds
     * @description: 给角色添加权限
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/28 11:17
     * @throws:
     **/
    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId, @RequestParam(name = "ids") String[] permissionIds) {
        roleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:findAll.do";
    }
}
