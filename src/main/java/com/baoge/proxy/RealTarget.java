package com.baoge.proxy;

public class RealTarget implements Target {
    @Override
    public void myRequest() {
        System.out.println("From real target.");
    }
}
