package com.dg.system.service;

import com.dg.common.service.IMpBaseService;
import com.dg.system.model.SysPermission;
import com.dg.system.model.request.LoginReq;
import com.dg.system.model.vo.LoginVo;
import com.dg.system.model.request.RegisterReq;
import com.dg.system.model.SysUser;

import java.util.List;

/**
 * @author ty
 */
public interface UserService extends IMpBaseService<SysUser> {
    SysUser getUserByUserName(String userName);

    List<SysPermission> getUserPermission(String userName);

    LoginVo login(LoginReq loginReq);

    void register(RegisterReq req);

    Integer insert(SysUser user);
}
