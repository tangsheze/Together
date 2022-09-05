package com.dg.system.userManager.model;

import lombok.Data;

import javax.management.relation.Role;
import java.util.List;

/**
 * @Author TheFool
 */
@Data
public class LoginVO {
    private String token;

    private Long id;

    private String userName;

    private String realName;

    private String sex;

    private String phoneNumber;

    private String age;

    private String avatarUrl;

    private String remark;

    private List<Role> roleList;
}
