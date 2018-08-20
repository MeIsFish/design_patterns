package com.zkx.proxy.custom;

import java.io.*;

public class ZClassLoader extends ClassLoader {


    private static File basdir;

    static {
        String path = ZClassLoader.class.getResource("").getPath();
        basdir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = ZClassLoader.class.getPackage().getName()+"."+name;
        File file;
        if (basdir.exists()) {
            file = new File(basdir, name+".class");
            FileInputStream in = null;
            ByteArrayOutputStream out = null;
            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len;
                while((len=in.read(buff))!=-1){
                    out.write(buff,0,len);
                }
                return defineClass(className,out.toByteArray(),0,out.size());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(in!=null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }if(out!=null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(file.exists()){
                    file.delete();
                }
            }
        }


        return super.findClass(name);
    }
}
