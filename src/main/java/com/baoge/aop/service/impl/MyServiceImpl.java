package com.baoge.aop.service.impl;

import com.baoge.aop.service.MyService;

public class MyServiceImpl implements MyService {
    @Override
    public void myMethod() {
        System.out.println("myMethod invoked.");
    }
}
