package top.yuwenxin.design.construct;

/**
 * 代理模式，增强类（需要实现接口）的一种方法
 *
 * 通过实现同一个接口，代理对象中保存一个被代理的对象，调用代理对象的接口方法即可以对被代理对象的方法进行增强
 * 代理对象必须实现被代理对象的同一个接口
 */
public class ProxyPattern {
    public static void main(String[] args) {
        Proxy proxy = new ProxyObj(new ProxiedSubject());
        proxy.print();
    }
}

interface Proxy{
    void print();
}

class ProxiedSubject implements Proxy{

    @Override
    public void print() {
        System.out.println("print...");
    }
}

class ProxyObj implements Proxy{
    ProxiedSubject subject;

    public ProxyObj(ProxiedSubject subject){
        this.subject = subject;
    }

    @Override
    public void print() {
        System.out.println("enhance...");
        System.out.println(subject.getClass().getName() + "由我来代理发言");
    }
}