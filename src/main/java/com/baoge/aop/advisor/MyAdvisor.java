package com.baoge.aop.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAdvisor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("before myAdvisor invoked.");
        Object result = invocation.proceed();
        System.out.println("after myAdvisor invoked.");
        return result;
    }
}
