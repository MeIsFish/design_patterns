package com.zkx.proxy.custom;

import java.lang.reflect.Method;

public interface ZInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
