package com.dg.common.datasource;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.dg.utils.IdTool;
import com.dg.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

/**
 * 日志创建时间自动填充处理类
 *
 * @author ty
 */
@Slf4j
@Configuration
public class MybatisObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {

        strictInsertFill(metaObject, "id", Long.class, IdTool.getId());
        strictInsertFill(metaObject, "createDate", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        strictInsertFill(metaObject, "createBy", String.class, SecurityUtil.getUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updateDate", Timestamp.class, new Timestamp(System.currentTimeMillis()));
        strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtil.getUsername());
    }
}

