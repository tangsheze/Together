package com.dg.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author ty
 */
@Repository
@Mapper
public interface RoleDao extends BaseMapper<SysRole> {
}
