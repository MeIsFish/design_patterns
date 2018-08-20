package com.zkx.proxy.custom;
import java.lang.reflect.Method;
import com.zkx.proxy.jdk.Person;
public class $Proxy0 extends ZProxy implements Person{
public $Proxy0(ZInvocationHandler h){
super(h);}
public void makeMoney(){
try{
Method m0 = Person.class.getMethod("makeMoney",new Class[]{});
super.h.invoke(this,m0,null);
}catch(Throwable e){
e.printStackTrace();
}
}
}