package com.dg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dg.common.exception.BusinessException;
import com.dg.common.exception.ExceptionCode;
import com.dg.common.security.TokenService;
import com.dg.common.service.IMpBaseServiceImpl;
import com.dg.model.SysPermission;
import com.dg.dao.RoleDao;
import com.dg.dao.UserDao;
import com.dg.model.SysUser;
import com.dg.model.dto.SysUserCheckDto;
import com.dg.model.request.LoginReq;
import com.dg.model.request.RegisterReq;
import com.dg.model.vo.LoginVo;
import com.dg.service.UserService;
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

/**
 * @author ty
 */
@Service
public class UserServiceImpl extends IMpBaseServiceImpl<UserDao, SysUser> implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokenService jwtTokenUtil;

    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SysUser getUserByUserName(String userName) {
        SysUser sysUser = userDao.selectOne(SysUser.qw().eq(SysUser::getUserName, userName));
        if (sysUser == null) {
            throw new BusinessException(ExceptionCode.SYS_UNAUTHORIZED, "用户不存在");
        }
        return sysUser;
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
    public LoginVo login(LoginReq loginReq) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUserName());
        if (!passwordEncoder.matches(loginReq.getPassword(), userDetails.getPassword())) {
            throw new BusinessException(ExceptionCode.SYS_UNAUTHORIZED, "用户名密码错误");
        }
        UsernamePasswordAuthenticationToken authentication
                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginVo copy = BeanHelper.copy(getUserByLoginName(loginReq.getUserName()), LoginVo.class);
        jwtTokenUtil.createToken(copy);
        return copy;
    }

    public SysUser getUserByLoginName(String userName) {
        SysUser sysUser = userDao.selectOne(SysUser.qw().eq(SysUser::getUserName, userName));
        if (sysUser == null) {
            throw new BusinessException(ExceptionCode.SYS_UNAUTHORIZED, "用户不存在");
        }
        return sysUser;
    }

    @Override
    public void register(RegisterReq req) {
        if (req.getUserName() != null && StringUtils.isNotBlank(req.getUserName())) {
            sysUserCheck(new SysUserCheckDto(req.getUserName()));
            req.setPassword(passwordEncoder.encode(req.getPassword()));
            SysUser copy = BeanHelper.copy(req, SysUser.class);
            insert(copy);
        }
    }

    private Boolean sysUserCheck(SysUserCheckDto dto) {
        if (count(SysUser.qw().eq(SysUser::getUserName, dto.getUserName())) > 0) {
            throw new BusinessException(ExceptionCode.PARAM_ERROR, "已存在该用户名");
        }
        return Boolean.TRUE;
    }


    @Override
    public Integer insert(SysUser user) {
        return userDao.insert(user);
    }
}
