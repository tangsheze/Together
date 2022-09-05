package com.dg.common.base;

import com.dg.common.exception.handle.UncaughtExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * 自定义线程异常捕获
 *
 * @Author TheFool
 */
@Slf4j
@Configuration
public class ThreadConfig {
    @Autowired
    UncaughtExceptionHandler uncaughtExceptionHandler;

    @EventListener(ApplicationReadyEvent.class)
    public void setUncaughtExceptionHandler() {
        log.info("set uncaughtExceptionHandler to UncaughtExceptionHandler...");
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }
}
