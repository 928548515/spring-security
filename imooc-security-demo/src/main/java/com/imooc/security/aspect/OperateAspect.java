package com.imooc.security.aspect;

import com.imooc.security.annotation.MyFirstAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class OperateAspect {
    @Pointcut("@annotation(com.imooc.security.annotation.MyFirstAnnotation)")
    public void annotationPointCut(){

    }


    @Around("annotationPointCut()")
    public Object advice(ProceedingJoinPoint joinPoint){
        System.out.println("通知之开始");
        Object retmsg=null;
        try {
            retmsg=joinPoint.proceed();
//            System.err.println("++++++++"+retmsg);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("通知之结束");
        return retmsg;
    }


    @Before("annotationPointCut()")
    public void Before(JoinPoint joinPoint){
        MethodSignature sign =  (MethodSignature)joinPoint.getSignature();
        Method method = sign.getMethod();
        MyFirstAnnotation annotation = method.getAnnotation(MyFirstAnnotation.class);
        System.out.println("打印："+annotation.value()+" 开始前");

    }



    @After("annotationPointCut()")
    public void after() {
        System.out.println("after方法执行后");
    }


    @AfterReturning(value = "annotationPointCut()",returning = "result")
    public void logReturn(JoinPoint joinPoint, Object result){
        System.out.println("除法正常返回... 返回结果：{"+result+"}");
    }

}
