package com.dg.common.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dg.common.exception.BusinessException;
import com.dg.common.exception.ExceptionCode;
import com.dg.common.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @author ty
 * @date 2022/9/9
 * @apiNote
 */
public class IMpBaseServiceImpl<MAPPER extends BaseMapper<ENTITY>, ENTITY extends BaseEntity> extends ServiceImpl<MAPPER, ENTITY> implements IMpBaseService<ENTITY> {

    private static final Logger log = LoggerFactory.getLogger(IMpBaseServiceImpl.class);

    public IMpBaseServiceImpl() {
    }


    @Override
    public IPage<ENTITY> queryPage(ENTITY entity, Integer pageNo, Integer pageSize) {
        QueryWrapper<ENTITY> queryWrapper = QueryGenerator.initQueryWrapper(entity);
        Page<ENTITY> page = new Page((long) pageNo, (long) pageSize);
        return this.page(page, queryWrapper);
    }

    @Override
    public ENTITY queryById(Long id) {
        ENTITY entity = this.getById(id);
        if (null == entity) {
            throw new BusinessException(ExceptionCode.SYS_ERROR, "未找到对应实体！");
        }
        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    @Override
    public void commit(ENTITY commitParam) {
        if (null == commitParam.getId()) {
            this.save(commitParam);
        } else {
            commitParam.cleanEntity();
            ENTITY entityInDb = this.getById(commitParam.getId());
            if (null == entityInDb) {
                throw new BusinessException(ExceptionCode.SYS_ERROR, "未找到对应实体！");
            } else {
                this.updateById(commitParam);
            }
        }
    }

    @Override
    public void delete(List<Long> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            throw new BusinessException(ExceptionCode.PARAM_ERROR, "参数为空！");
        } else {
            this.removeByIds(idList);
        }
    }
}
