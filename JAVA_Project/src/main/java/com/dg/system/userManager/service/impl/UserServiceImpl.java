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
import com.dg.system.roleManager.service.SysUserRoleService;
import com.dg.system.userManager.dao.UserDao;
import com.dg.system.userManager.model.*;
import com.dg.system.userManager.service.UserService;
import com.dg.utils.BeanHelper;
import com.dg.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

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

    @Override
    public LoginVO login(LoginReq loginReq) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUserName());
        if (!passwordEncoder.matches(loginReq.getPassword(), userDetails.getPassword())) {
            throw new BusinessException(ExceptionCode.SYS_UNAUTHORIZED, "用户名密码错误");
        }
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(userDetails.toString());
        String token = jwtTokenUtil.generateToken(userDetails);
        LoginVO copy = BeanHelper.copy(getUserByLoginName(loginReq.getUserName()), LoginVO.class);
        copy.setToken(token);
        return copy;
    }

    public SysUser getUserByLoginName(String userName) {
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

    @Override
    public void register(RegisterReq req) {
        if (req.getUserName() != null && StringUtils.isNotBlank(req.getUserName())) {
            sysUserCheck(new SysUserCheckDto(req.getUserName()));

            req.setPassword(passwordEncoder.encode(req.getPassword()));
            SysUser copy = SysUser.fromSysUser(BeanHelper.copy(req, SysUser.class));

            if (insert(copy) != 1) {
                throw new BusinessException(ExceptionCode.SYS_ERROR, "注册失败,用户插入失败");
            }

            if (sysUserRoleService.insert(new SysUserRole(copy.getId())) != 1) {
                throw new BusinessException(ExceptionCode.SYS_ERROR, "注册失败，角色插入失败");
            }
        }
    }

    private Boolean sysUserCheck(SysUserCheckDto dto) {
        LambdaQueryWrapper<SysUser> query = new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUserName, dto.getUserName());
        if (userDao.selectCount(query) != 0) {
            throw new BusinessException(ExceptionCode.PARAM_ERROR, "已存在该用户名");
        }
        return Boolean.TRUE;
    }


    @Override
    public Integer insert(SysUser user) {
        return userDao.insert(user);
    }
}
