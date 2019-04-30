package com.itheima.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 用户实体
 * @Date: 2019/04/26 18:03
 * @Modified By:
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -4681237066676324421L;
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private int status;
    private String statusStr;
    private List<Role> roles;

}
