package com.dg.system.userManager.service;

import com.dg.system.permissionManager.model.SysPermission;
import com.dg.system.userManager.model.LoginReq;
import com.dg.system.userManager.model.LoginVO;
import com.dg.system.userManager.model.RegisterReq;
import com.dg.system.userManager.model.SysUser;

import java.util.List;

/**
 * @Author TheFool
 */
public interface UserService {
    SysUser getUserByUserName(String userName);

    List<SysPermission> getUserPermission(String userName);

    LoginVO login(LoginReq loginReq);

    void register(RegisterReq req);

    Integer insert(SysUser user);
}
