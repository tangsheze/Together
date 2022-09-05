package com.dg.common.exception.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 日志创建时间自动填充处理类
 *
 * @author The Fool
 */
@Slf4j
@Configuration
public class MybatisObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("star insert fill...");
        this.setFieldValByName("create_time",new Date(),metaObject);
        this.setFieldValByName("update_time",new Date(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("star update fill...");
        //setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        this.setFieldValByName("update_time",new Date(),metaObject);
    }
}

