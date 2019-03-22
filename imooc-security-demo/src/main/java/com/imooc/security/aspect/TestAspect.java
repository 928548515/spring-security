package com.imooc.security.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {


    @Pointcut("execution(* com.imooc.security.controller.TestCntroller.*(..))")
    public void pointcut(){}


    @Around("pointcut()")
    public Object advice(ProceedingJoinPoint point){
        System.out.println("测试通知之开始");
        Object proceed=null;
        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("测试通知之结束");
        return proceed;
    }

    @Before("pointcut()")
    public void Before(JoinPoint joinPoint){
        System.out.println("测试通知准备中。。。。");
    }

    @After("pointcut()")
    public void After(){
        System.out.println("测试通知执行后。。。。");
    }

    @AfterReturning(pointcut = "pointcut()",returning = "result")
    public void AfterReturning(JoinPoint joinPoint,Object result){
        System.out.println("测试正常返回... 返回结果：{"+result+"}");
    }
}
