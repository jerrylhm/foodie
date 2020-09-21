package com.lhm.aspect;

import com.imooc.utils.MD5Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lhm.service.impl..*.*(..))")
    public void recordTimeAspect() {

    }

    @Around("recordTimeAspect()")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("{}——{}: {}毫秒", joinPoint.getTarget().getClass()
            , joinPoint.getSignature().getName(), endTime - startTime);
        return result;
    }

    public static void main(String[] args) {
        String md5Str = null;
        try {
            md5Str = MD5Utils.getMD5Str("123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(md5Str);
    }
}
