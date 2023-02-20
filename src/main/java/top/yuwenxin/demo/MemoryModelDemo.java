package top.yuwenxin.demo;

import org.openjdk.jol.info.ClassLayout;

public class MemoryModelDemo {

     public static void main(String[] args) {
        Obj obj = new Obj();
        obj.s = "123";
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    static class Obj {
        int i;
        long l;
        boolean b;
        char c;
        String s = "1";
    }
}
