package top.yuwenxin.jdk.reflect;

import java.lang.reflect.Field;

public class Fields {
    public static void main(String[] args) {
        Class clz = Obj.class;
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field f :
                declaredFields) {
            System.out.println(f.toString());
        }

        System.out.println("----------------");

        Field[] fields = clz.getFields();
        for (Field f :
                fields) {
            System.out.println(f.toString());
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");

        Class clz2 = Son.class;
        Field[] declaredFields2 = clz2.getDeclaredFields();
        for (Field f :
                declaredFields2) {
            System.out.println(f.toString());
        }

        System.out.println("----------------");

        Field[] fields2 = clz2.getFields();
        for (Field f :
                fields2) {
            System.out.println(f.toString());
        }
        System.out.println("-----------------");
        Class clz3 = clz2.getSuperclass();
        Field[] declaredFields1 = clz3.getDeclaredFields();
        for (Field f : declaredFields1) {
            System.out.println(f.toString());
        }

    }
}
