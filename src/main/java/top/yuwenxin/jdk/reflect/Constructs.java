package top.yuwenxin.jdk.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Constructs {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clz = Obj.class;
        Constructor[] constructors = clz.getConstructors();
        Constructor constructor = null;
        for (Constructor c :
                constructors) {
            System.out.println(c.getName());
            if (c.getParameters().length == 0) {
                constructor = c;
            }
        }
        if (constructor != null){
            Obj obj = (Obj) constructor.newInstance();
            System.out.println(obj.getClass());
        }
    }
}
