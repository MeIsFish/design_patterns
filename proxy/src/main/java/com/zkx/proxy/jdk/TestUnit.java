package com.zkx.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class TestUnit {


    public static void main(String[] args) throws Exception{

        Computer c = new Computer();
        Object obj = c.getInstance(new Boy());
        Person proxy = (Person) obj;
        proxy.makeMoney();
        byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        FileOutputStream out = new FileOutputStream("E:\\IdeaProjects\\DesignPattern\\src\\main\\java\\com\\zkx\\dp\\proxy\\jdk\\$Proxy0.class");
        out.write(data);
        out.flush();
        out.close();
    }
}
