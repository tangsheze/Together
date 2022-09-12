package com.dg.common.exception.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 未捕获异常处理
 *
 * @author ty
 * */
@Slf4j
@Component
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        log.error("Exception has been raised by Name: {}, Id: {}", t.getName(), t.getId(), ex);
    }
}
