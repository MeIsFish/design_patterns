package com.zkx.proxy.custom;

import com.zkx.proxy.jdk.Person;

import java.lang.reflect.Method;

public class ZComputer implements ZInvocationHandler {
    private Person target;

    public Person getInstance( Person person ) {
        this.target = person;

        return (Person) ZProxy.newProxyInstance(new ZClassLoader(), person.getClass().getInterfaces(), this);
    }



    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        learnComputer();

        return method.invoke(target, args);
    }


    private void learnComputer(){
        System.out.println("learn computer so hard");
    }
}
