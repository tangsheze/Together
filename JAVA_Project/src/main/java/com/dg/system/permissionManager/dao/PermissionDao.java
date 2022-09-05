package com.dg.system.permissionManager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.system.permissionManager.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author TheFool
 */
@Repository
@Mapper
public interface PermissionDao extends BaseMapper<SysPermission> {
}
