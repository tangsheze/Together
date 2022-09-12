package com.dg.service;

import com.dg.common.service.IMpBaseService;
import com.dg.model.SysPermission;
import com.dg.model.request.LoginReq;
import com.dg.model.vo.LoginVo;
import com.dg.model.request.RegisterReq;
import com.dg.model.SysUser;

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
