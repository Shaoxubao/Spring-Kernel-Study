package com.baoge.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JVM参数（将动态代理生成的字节码类保存在磁盘上）：
 * -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
 */
public class DynamicProxyClient {
    public static void main(String[] args) {
        RealSubject realSubject = new RealSubject();

        InvocationHandler invocationHandler = new DynamicSubject(realSubject);

        Class<?> classType = invocationHandler.getClass();

        Subject subject = (Subject) Proxy.newProxyInstance(classType.getClassLoader(),
                realSubject.getClass().getInterfaces(), invocationHandler);

        subject.myRequest();

        System.out.println(subject.getClass());
    }
}
