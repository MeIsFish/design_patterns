package com.zkx.proxy.custom;

import com.zkx.proxy.jdk.Boy;
import com.zkx.proxy.jdk.Person;

public class TestUnit {


    public static void main(String[] args) {

        Person proxy = new ZComputer().getInstance(new Boy());
        proxy.makeMoney();
    }

}
