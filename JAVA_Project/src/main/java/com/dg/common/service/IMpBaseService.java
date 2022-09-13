package com.dg.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ty
 * @apiNote
 */
public interface IMpBaseService<ENTITY> extends IService<ENTITY> {

    IPage<ENTITY> queryPage(ENTITY entity, Integer pageNo, Integer pageSize);

    ENTITY queryById(Long id);

    void commit(ENTITY commitParam);

    void delete(List<Long> idList);

}
