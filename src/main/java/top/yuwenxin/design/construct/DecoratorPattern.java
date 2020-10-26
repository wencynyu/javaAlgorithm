package top.yuwenxin.design.construct;

/**
 * 装饰器模式（Decorator Pattern）允许向一个现有的对象添加新的功能，同时又不改变其结构。
 * 这种类型的设计模式属于结构型模式，它是作为现有的类的一个包装。
 *
 * 和代理模式非常相似，都是用来增强对象使用。
 * 但是装饰器模式通过一个抽象类，内部保存被装饰的接口，外加一些增强方法进行实现增强效果，调用增强方法即可以调用被装饰对象的原始方法。
 * 这样可以对需要实现增强的功能进行统一的实现。
 * 此外，装饰器的增强类不需要实现被装饰对象的接口。
 *
 * @Important: 在jdk内部的io流设计中大量采用了装饰器模式！！！在BufferedInput/OutputStream中保留了一个InputStream接口
 */
public class DecoratorPattern {
    public static void main(String[] args) {
        DecoratorObj decoratorObj = new DecoratorObj(new DecoratedSubject());
        decoratorObj.enhance();
    }
}

interface Decorator{
    void print();
}

class DecoratedSubject implements Decorator{
    @Override
    public void print() {
        System.out.println("print...");
    }
}

abstract class AbsDecorator{
    Decorator decorator;
    public AbsDecorator(Decorator decorator){
        this.decorator = decorator;
    }

    abstract void enhance();
}

class DecoratorObj extends AbsDecorator {
    Decorator decorator;

    public DecoratorObj(Decorator decorator) {
        super(decorator);
    }

    @Override
    void enhance() {
        System.out.println("enhance...");
        decorator.print();
    }


}