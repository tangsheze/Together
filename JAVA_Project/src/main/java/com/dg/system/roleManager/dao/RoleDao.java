package com.dg.system.roleManager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.system.roleManager.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author TheFool
 */
@Repository
@Mapper
public interface RoleDao extends BaseMapper<SysRole> {
}