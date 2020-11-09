package top.yuwenxin.jdk.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Methods {
    public static void main(String[] args) throws Exception {
        Class clz = Obj.class;
        Method[] declaredMethods = clz.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        System.out.println("--------------");

        Method[] methods = clz.getMethods();
        for (Method m :
                methods) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


        Class clz2 = Son.class;
        Method[] declaredMethods2 = clz2.getDeclaredMethods();
        for (Method m :
                declaredMethods2) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        System.out.println("--------------");

        Method[] methods2 = clz2.getMethods();
        for (Method m :
                methods2) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");

        Class clz3 = clz2.getSuperclass();
        Method[] declaredMethods3 = clz3.getDeclaredMethods();
        for (Method m :
                declaredMethods3) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        System.out.println("--------------");

        Method[] methods3 = clz3.getMethods();
        for (Method m :
                methods3) {
            System.out.println(m.toString());
            Parameter[] parameters = m.getParameters();
            for (Parameter p :
                    parameters) {
                System.out.println(p.toString());
            }
        }

        Obj o = (Obj) clz.getConstructor().newInstance();

        // java 自省机制
        PropertyDescriptor descriptor = new PropertyDescriptor("s", clz);
        Method writeMethod = descriptor.getWriteMethod();
        writeMethod.invoke(o, "hello reflection");
        Method readMethod = descriptor.getReadMethod();
        readMethod.invoke(o);
        System.out.println(o);

        // 方法调用
        Method m = clz.getMethod("method", int.class, String.class, double.class);
        m.invoke(o,1, "123",1.3);
    }
}
