package com.dg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ty
 */
@Repository
@Mapper
public interface PermissionDao extends BaseMapper<SysPermission> {
}
