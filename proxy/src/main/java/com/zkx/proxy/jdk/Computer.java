package com.zkx.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Computer implements InvocationHandler {

    private Person target;

    public Object getInstance( Person person) {
        this.target = person;
        Class<? extends Person> clazz = person.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        learnComputer();
        return method.invoke(target, args);
    }


    private void learnComputer(){
        System.out.println("learn computer so hard");
    }

}
