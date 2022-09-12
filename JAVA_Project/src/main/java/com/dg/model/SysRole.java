package com.dg.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dg.common.model.BaseEntity;
import lombok.Data;

import java.util.List;

/**
 * @author ty
 */
@Data
@TableName(value = "sys_role")
public class SysRole extends BaseEntity {

    public static LambdaQueryWrapper<SysRole> qw() {
        return new LambdaQueryWrapper<>();
    }

    private String roleName;

    private String roleCode;

    private String roleStatus;

    private String remark;

    @TableField(exist = false)
    private transient List<SysPermission> permissionList;
}
