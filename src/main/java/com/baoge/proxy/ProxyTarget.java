package com.baoge.proxy;

public class ProxyTarget implements Target {

    private RealTarget realTarget;

    @Override
    public void myRequest() {
        this.beforeRequest();

        if (realTarget == null) {
            realTarget = new RealTarget();
        }

        realTarget.myRequest();

        this.afterRequest();
    }

    private void beforeRequest() {
        System.out.println("before request...");
    }

    private void afterRequest() {
        System.out.println("after request...");
    }
}
