package com.dg.system.roleManager.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dg.system.permissionManager.model.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * @Author TheFool
 */
@Data
@TableName(value = "sys_role")
public class SysRole {

    private Long id;

    private String roleName;

    private String roleCode;

    private String roleStatus;

    private String remark;

    @TableField(exist = false)
    private transient List<SysPermission> permissionList;
}
