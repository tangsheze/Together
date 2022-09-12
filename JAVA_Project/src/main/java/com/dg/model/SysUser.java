package com.dg.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dg.common.model.BaseEntity;
import lombok.Data;

/**
 * @author ty
 */
@Data
@TableName(value = "sys_user")
public class SysUser extends BaseEntity {

    public static LambdaQueryWrapper<SysUser> qw() {
        return new LambdaQueryWrapper<>();
    }

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

    private Long roleId;

}
