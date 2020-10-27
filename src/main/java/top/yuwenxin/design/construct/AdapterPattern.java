package top.yuwenxin.design.construct;

/**
 * 通常用在扩展原有系统时
 */
public class AdapterPattern {
    public static void main(String[] args) {
        Adapter adapter = new Adapter(new FuncImpl1());
        adapter.adaptCall();
    }
}

interface Func {
    void func1();
    void func2();
}

class FuncImpl1 implements Func {

    @Override
    public void func1() {
        System.out.println("func1...");
    }

    @Override
    public void func2() { }
}

class FuncImpl2 implements Func {

    @Override
    public void func1() { }

    @Override
    public void func2() {
        System.out.println("func2...");
    }
}

class Adapter{
    Func func;

    public Adapter(Func func){
        this.func = func;
    }

    public void adaptCall(){
        if (func.getClass().getName().equals("top.yuwenxin.design.construct.FuncImpl1")){
            func.func1();
        }else if (func.getClass().getName().equals("top.yuwenxin.design.construct.FuncImpl2")){
            func.func2();
        }else {
            System.out.println("没有实现的方法类");
        }
    }
}
