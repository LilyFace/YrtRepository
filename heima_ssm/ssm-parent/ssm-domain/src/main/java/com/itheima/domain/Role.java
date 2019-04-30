package com.itheima.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 角色实体
 * @Date: 2019/04/26 18:04
 * @Modified By:
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 9112633700908262290L;
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<User> users;
}
