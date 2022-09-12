package com.dg.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.dg.utils.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * @author ty
 * @date 2022/9/9
 * @apiNote
 */
public class QueryGenerator {
    private static final Logger log = LoggerFactory.getLogger(QueryGenerator.class);

    public QueryGenerator() {
    }

    public static <T> QueryWrapper<T> initQueryWrapper(T entity) {
        log.debug("function=initQueryWrapper, entity={}", entity);
        QueryWrapper queryWrapper = new QueryWrapper();

        try {
            installQuery(queryWrapper, entity);
        } catch (IllegalAccessException var4) {
            var4.printStackTrace();
        }
        return queryWrapper;
    }

    private static <T> void installQuery(QueryWrapper<T> queryWrapper, T entity) throws IllegalAccessException {
        if (entity != null) {
            Class<?> entityClass = entity.getClass();
            Field[] declaredFields = getAllFields(entityClass);
            List<Field> allList = new ArrayList(Arrays.asList(declaredFields));
            Iterator var5 = allList.iterator();

            while(var5.hasNext()) {
                Field field = (Field)var5.next();
                field.setAccessible(true);
                Object object = field.get(entity);
                if (object != null) {
                    String fieldName = field.getName();
                    String column = ConvertUtil.camelToUnderline(fieldName);
                    if (object instanceof String) {
                        String fieldValue = (String)object;
                        if (!ConvertUtil.isEmpty(fieldValue)) {
                            queryWrapper.like(column, fieldValue);
                        }
                    } else if (!(object instanceof Date) && !(object instanceof LocalDate) && !(object instanceof LocalDateTime) && !(object instanceof LocalTime)) {
                        queryWrapper.eq(column, object);
                    }
                    field.setAccessible(false);
                }
            }

        }
    }

    private static Field[] getAllFields(Class<?> clazz) {
        ArrayList fieldList;
        for(fieldList = new ArrayList(); clazz != null; clazz = clazz.getSuperclass()) {
            fieldList.addAll(new ArrayList(Arrays.asList(clazz.getDeclaredFields())));
        }

        Field[] fields = new Field[fieldList.size()];
        return (Field[])fieldList.toArray(fields);
    }
}

