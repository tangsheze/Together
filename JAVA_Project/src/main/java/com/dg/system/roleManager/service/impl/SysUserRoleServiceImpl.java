package com.dg.system.roleManager.service.impl;

import com.dg.system.roleManager.dao.UserRoleDao;
import com.dg.system.roleManager.model.SysUserRole;
import com.dg.system.roleManager.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author TheFool
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {


    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public Integer insert(SysUserRole sysUserRole) {
        return userRoleDao.insert(sysUserRole);
    }


}
