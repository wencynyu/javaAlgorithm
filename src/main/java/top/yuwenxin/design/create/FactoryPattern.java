package top.yuwenxin.design.create;

/**
 * 工厂模式
 * 隐藏对象实例化方式
 */
public class FactoryPattern {
    public static void main(String[] args) {
        Instance instance1 = Factory.getInstance("instance1");
        instance1.print();
    }
}

class Factory{
    public static Instance getInstance(String name) {
        if (name.equals("instance1")){
            return new Instance1();
        }
        if (name.equals("instance2")){
            return new Instance2();
        }
        if (name.equals("instance3")){
            return new Instance3();
        }
        return null;
    }
}

interface Instance{
    void print();
}

class Instance1 implements Instance{
    @Override
    public void print() {
        System.out.println(this.getClass().getName());
    }
}

class Instance2 implements Instance{
    @Override
    public void print() {
        System.out.println(this.getClass().getName());
    }
}

class Instance3 implements Instance{
    @Override
    public void print() {
        System.out.println(this.getClass().getName());
    }

}
