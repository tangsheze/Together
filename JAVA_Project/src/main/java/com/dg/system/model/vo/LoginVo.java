package com.dg.system.model.vo;

import lombok.Data;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author ty
 */
@Data
public class LoginVo {
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

    private Long loginTime;
    /**
     * 过期时间
     */
    private Long expireTime;
}
