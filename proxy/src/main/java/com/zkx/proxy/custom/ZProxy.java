package com.zkx.proxy.custom;


import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ZProxy {


    protected ZInvocationHandler h;
    private ZProxy() {
    }

    public ZProxy(ZInvocationHandler h) {
        this.h = h;
    }

    public static Object newProxyInstance(ZClassLoader loader, Class<?>[] interfaces, ZInvocationHandler h) {
        File file = null;

        try {
            // 1.生成代理类源代码
            String source = generateProxySrc(interfaces[0]);
            // 2.生成代理类java文件
            String path = ZProxy.class.getResource("").getPath();
            file = new File(path + "$Proxy0.java");
            FileOutputStream out = new FileOutputStream(file);
            out.write(source.getBytes());
            out.flush();
            out.close();
            // 3.编译成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> iterable = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();
            // 4.将class文件加载到JVM中

            // 5.返回代理类的引用
            Class<?> proxy0 = loader.findClass("$Proxy0");
            Constructor<?> con = proxy0.getConstructor(ZInvocationHandler.class);
            return con.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(file != null){
                file.delete();
            }
        }


        return null;
    }

    private static String generateProxySrc(Class interfaces) {
        String enter = "\r\n";
        StringBuilder sb = new StringBuilder();
        sb.append("package com.zkx.dp.proxy.custom;").append(enter);
        sb.append("import java.lang.reflect.Method;").append(enter);
        sb.append("import Person;").append(enter);
        sb.append("public class $Proxy0 extends ZProxy implements ").append(interfaces.getSimpleName()).append("{").append(enter);
        sb.append("public $Proxy0(ZInvocationHandler h){").append(enter);
        sb.append("super(h);}").append(enter);
        for (Method method : interfaces.getMethods()) {
            sb.append("public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append("(){").append(enter)
                    .append("try{").append(enter)
                    .append("Method m0 = ").append(interfaces.getSimpleName()).append(".class.getMethod(").append("\""+method.getName()+"\","+"new Class[]{});").append(enter)
                    .append("super.h.invoke(this,m0,null);").append(enter)
                    .append("}").append("catch(Throwable e){").append(enter)
                    .append("e.printStackTrace();").append(enter)
                    .append("}").append(enter)
                    .append("}").append(enter);
        }
        sb.append("}");
        return sb.toString();
    }

}
