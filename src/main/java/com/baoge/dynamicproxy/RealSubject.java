package com.baoge.dynamicproxy;

public class RealSubject implements Subject {
    @Override
    public void myRequest() {
        System.out.println("myRequest invoke.");
    }
}
