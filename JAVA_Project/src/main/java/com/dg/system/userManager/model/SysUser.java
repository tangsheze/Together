package com.dg.system.userManager.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dg.system.roleManager.model.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @Author TheFool
 */
@Data
@TableName(value = "sys_user")
public class SysUser {
    private Long id;

    private String userName;

    private String password;

    private String email;

    private String realName;

    private String sex;

    private String phoneNumber;

    private String age;

    private String avatarUrl;

    private String status;

    private String remark;

    @TableField(exist = false)
    private transient List<SysRole> roleList;
}
