package com.dg.system.permissionManager.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author TheFool
 */
@Data
@TableName(value = "sys_permission")
public class SysPermission {

    private Long id;

    private String permissionName;

    private String permissionCode;

    private String permissionStatus;

    private String remark;
}
