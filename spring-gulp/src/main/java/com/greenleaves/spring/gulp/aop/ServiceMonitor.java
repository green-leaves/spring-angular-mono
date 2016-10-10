package com.greenleaves.spring.gulp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by sgdn001 on 9/28/2016.
 */
@Aspect
@Component
public class ServiceMonitor {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Around("within(com.greenleaves.spring.gulp.service..*)")
    public Object logAnyService(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Before: " + joinPoint.toShortString());
        logger.info("Parameter: " + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();
        if (result != null){
            logger.info("Return: " + result.toString());
        }

        //logger.info("After: " + joinPoint.toShortString());

        return result;
    }
}
