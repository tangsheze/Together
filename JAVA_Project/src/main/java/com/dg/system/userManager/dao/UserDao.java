package com.dg.system.userManager.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dg.system.permissionManager.model.SysPermission;
import com.dg.system.userManager.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author TheFool
 */
@Repository
@Mapper
public interface UserDao extends BaseMapper<SysUser> {
    @Select("SELECT\n" +
            "\tsp.* \n" +
            "FROM\n" +
            "\tsys_user su\n" +
            "\tLEFT JOIN sys_user_role sur ON su.id = sur.user_id\n" +
            "\tLEFT JOIN sys_role sr ON sr.id = sur.role_id\n" +
            "\tLEFT JOIN sys_role_permission srp ON sr.id = srp.role_id \n" +
            "\tLEFT JOIN sys_permission sp ON srp.permission_id = sp.id\n" +
            "WHERE\n" +
            "\tsu.`status` = '0'\n" +
            "\tAND sr.role_status = '0'\n" +
            "\tAND sp.permission_status = '0'\n" +
            "\tAND su.user_name = #{userName}")
    List<SysPermission> getUserPermission(@Param("userName")String userName);
}
