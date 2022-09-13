package com.dg.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.system.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ty
 */
@Repository
@Mapper
public interface RoleDao extends BaseMapper<SysRole> {
}
