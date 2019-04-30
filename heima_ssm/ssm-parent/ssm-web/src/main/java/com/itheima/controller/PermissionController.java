package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 权限控制层
 * @Date: 2019/04/27 9:48
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@Controller
@RequestMapping("/permission")
public class PermissionController {
    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * @param model
     * @description: 查询所有权限
     * @return: java.lang.String
     * @author: YangRunTao
     * @date: 2019/04/26 20:23
     * @throws:
     **/
    @RequestMapping("/findAll.do")
    public String findAll(Model model) {
        List<Permission> list = null;
        try {
            list = permissionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("permissionList", list);
        return "permission-list";
    }

    /**
     * @param
     * @description: 保存权限
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/26 20:40
     * @throws:
     **/
    @RequestMapping("/save.do")
    public String save(Permission permission) {
        try {
            permissionService.save(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }
}
