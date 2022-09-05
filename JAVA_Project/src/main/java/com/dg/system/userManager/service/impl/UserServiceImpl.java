package com.dg.system.userManager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dg.common.exception.BusinessException;
import com.dg.common.exception.ExceptionCode;
import com.dg.system.permissionManager.dao.PermissionDao;
import com.dg.system.permissionManager.model.SysPermission;
import com.dg.system.roleManager.dao.RoleDao;
import com.dg.system.roleManager.dao.UserRoleDao;
import com.dg.system.roleManager.model.SysRole;
import com.dg.system.roleManager.model.SysUserRole;
import com.dg.system.userManager.dao.UserDao;
import com.dg.system.userManager.model.SysUser;
import com.dg.system.userManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author TheFool
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    UserRoleDao userRoleDao;

    PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SysUser getUserByUserName(String userName) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>();
        wrapper.eq(SysUser::getUserName, userName);
        SysUser sysUser = userDao.selectOne(wrapper);
        if (sysUser == null) {
            throw new BusinessException(ExceptionCode.SYS_UNAUTHORIZED, "用户不存在");
        }
        LambdaQueryWrapper<SysUserRole> userRoleWrapper = new LambdaQueryWrapper<SysUserRole>();
        userRoleWrapper.eq(SysUserRole::getUserId, sysUser.getId());
        sysUser.setRoleList(userRoleDao.selectList(userRoleWrapper).stream()
                .map(userRole -> getRole(userRole.getRoleId()))
                .collect(Collectors.toList()));
        return sysUser;
    }

    private SysRole getRole(Long roleId) {
        return roleDao.selectById(roleId);
    }

    @Override
    public List<SysPermission> getUserPermission(String userName) {
        List<SysPermission> userPermission = userDao.getUserPermission(userName);
        if (userPermission.size() == 0) {
            throw new BusinessException(ExceptionCode.SYS_VALID, "该用户不具备任何权限");
        }
        return userPermission;
    }
}
