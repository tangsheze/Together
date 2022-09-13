package com.dg.system.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dg.common.model.BaseEntity;
import lombok.Data;

/**
 * @author ty
 */
@Data
@TableName(value = "sys_permission")
public class SysPermission extends BaseEntity {

    public static LambdaQueryWrapper<SysPermission> qw() {
        return new LambdaQueryWrapper<>();
    }

    private String permissionName;

    private String permissionCode;

    private String permissionStatus;

    private String remark;
}
