package top.yuwenxin.design.action;

/**
 * 根据不同情况执行不同的执行策略
 */
public class StrategyPattern {
    public static void main(String[] args) {
        Context context = new Context(new FileIO());
        context.executeStrategy();

        context = new Context(new DataBaseIO());
        context.executeStrategy();

        context = new Context(new CacheIO());
        context.executeStrategy();
    }
}

interface IOStrategy{
    void doOperation();
}

class FileIO implements IOStrategy{

    @Override
    public void doOperation() {
        System.out.println("文件IO");
    }
}

class DataBaseIO implements IOStrategy{

    @Override
    public void doOperation() {
        System.out.println("数据库IO");
    }
}

class CacheIO implements IOStrategy{

    @Override
    public void doOperation() {
        System.out.println("内存IO");
    }
}

// context中保存策略的抽象接口，调用execute方法进行实际方法的调用
class Context{
    private IOStrategy ioStrategy;

    public Context(IOStrategy strategy){
        this.ioStrategy = strategy;
    }

    public void executeStrategy(){
        ioStrategy.doOperation();
    }
}