package org.clevertec.aspect.logger;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Parameter;
import java.util.Arrays;

@Aspect
@Slf4j
public class ServiceLogger {

    @AfterReturning("execution(public * org.clevertec.service.*.save())")
    public void afterSaveRecord(JoinPoint joinPoint) {

        log.info(Arrays.toString(getInputParameters(joinPoint)) + getReturnType(joinPoint));
    }

    public String getReturnType(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        return methodSignature.getMethod().getGenericReturnType().getTypeName();
    }

    public Parameter[] getInputParameters(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        return methodSignature.getMethod().getParameters();
    }
}
