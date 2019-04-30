package com.itheima.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 权限实体
 * @Date: 2019/04/26 18:05
 * @Modified By:
 */
@Data
public class Permission implements Serializable {

    private static final long serialVersionUID = -1875491925469509234L;
    private String id;
    private String permissionName;
    private String url;
    private List<Role> roles;
}
