package com.dg.system.userManager.service;

import com.dg.system.permissionManager.model.SysPermission;
import com.dg.system.userManager.model.SysUser;

import java.util.List;

/**
 * @Author TheFool
 */
public interface UserService {
    SysUser getUserByUserName(String userName);

    List<SysPermission> getUserPermission(String userName);
}
