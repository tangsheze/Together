package com.dg.system.roleManager.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author The Fool
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -1L;

    private Long userId;

    private Long roleId;

    public SysUserRole(Long userId) {
        this.userId = userId;
        this.roleId = 2L;
    }
}
