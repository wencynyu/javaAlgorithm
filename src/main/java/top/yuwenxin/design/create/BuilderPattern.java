package top.yuwenxin.design.create;


/**
 * 建造者模式
 * 提供链式的属性构造方法
 * 主要步骤为：创建一个内部类
 * 内部类的setter方法返回自身对象
 * 并且提供一个最终构造方法build()
 */
public class BuilderPattern {
    public static void main(String[] args) {
        BuildOuter.BuilderInner builder = new BuildOuter.BuilderInner();
        BuildOuter outer = builder.setAttr(1).build();
        System.out.println(outer);
    }
}


class BuildOuter{
    private int attr;
    static class BuilderInner{
        private int attr;
        public BuildOuter build(){
            return new BuildOuter(attr);
        }
        public BuilderInner(){}

        public int getAttr() {
            return attr;
        }

        public BuilderInner setAttr(int attr) {
            this.attr = attr;
            return this;
        }
    }

    public BuildOuter(){}
    public BuildOuter(int attr) {
        this.attr = attr;
    }

    @Override
    public String toString() {
        return "BuildOuter{" +
                "attr=" + attr +
                '}';
    }
}
