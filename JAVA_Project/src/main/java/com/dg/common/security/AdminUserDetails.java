package com.dg.common.security;

import com.dg.system.model.SysPermission;
import com.dg.system.model.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户详情类
 *
 * @author ty
 */
@Data
public class AdminUserDetails implements UserDetails {

    private SysUser sysUser;

    private List<SysPermission> permissionList;

    public AdminUserDetails() {}

    public AdminUserDetails(SysUser sysUser, List<SysPermission> permissionList) {
        this.sysUser = sysUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream()
                .filter(permission -> permission.getPermissionCode() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionCode()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return sysUser.getStatus().equals("0");
    }

}
