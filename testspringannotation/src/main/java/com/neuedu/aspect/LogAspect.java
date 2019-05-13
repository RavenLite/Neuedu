package com.neuedu.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

    //aspectJ weaving syntax
    //execution(* com.neuedu.model.service.*.*(..))
    //stands for the before method will be weaved into all methods under classes of com.neuedu.model.service

    @Before("execution(* com.neuedu.model.service.*.*(..))")
    public void before()
    {
        //before the method executes:
        System.out.println("methods before");
    }

    @After("execution(* com.neuedu.model.service.*.*(..))")
    public void after()
    {
        //after the method executes:
        System.out.println("methods after");
    }

}