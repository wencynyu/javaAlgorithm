package top.yuwenxin.design.action;

import java.util.Arrays;

public class ChainPattern {

    public static void main(String[] args) {
        PipeLine pipeLine = new PipeLine();

        pipeLine.addLast(new Chain1());
        pipeLine.addLast(new Chain2());

        pipeLine.startProcessChain("start process...");
    }
}

abstract class AbstractChainNode{

    protected int chainLevel;

    public AbstractChainNode(int chainLevel){
        this.chainLevel = chainLevel;
    }

    public int getChainLevel() {
        return this.chainLevel;
    }

    protected abstract void doChain(HandlerChainContext ctx, Object... args);
}

class HandlerChainContext{
    HandlerChainContext next;
    AbstractChainNode chainNode;

    public HandlerChainContext(AbstractChainNode chainNode){
        this.chainNode = chainNode;
    }

    void handler(Object arg0){
        this.chainNode.doChain(this, arg0);
    }

    void getNextContext(Object arg0){
        if (this.next != null){
            this.next.handler(arg0);
        }
    }
}

class Chain1 extends AbstractChainNode {

    public Chain1() {
        super(1);
    }


    @Override
    protected void doChain(HandlerChainContext ctx, Object... args) {
        System.out.println("chain: " + chainLevel + "...");
        System.out.println("get args:" + Arrays.toString(args));
        ctx.getNextContext(args);
    }
}

class Chain2 extends AbstractChainNode{

    public Chain2() {
        super(2);
    }

    @Override
    protected void doChain(HandlerChainContext ctx, Object... args) {
        System.out.println("chain: " + chainLevel + "...");
        System.out.println("get args:" + Arrays.toString(args));
        ctx.getNextContext(args);
    }
}

class PipeLine{
    private HandlerChainContext head = null;

    public PipeLine(){
        this.head = new HandlerChainContext(new AbstractChainNode(0) {
            @Override
            protected void doChain(HandlerChainContext ctx, Object... args) {
                System.out.println(args[0].toString());
                ctx.getNextContext(args);
            }
        });
    }

    public void startProcessChain(Object arg0){
        this.head.handler(arg0);
    }

    public void addLast(AbstractChainNode node){
        HandlerChainContext ctx = head;
        while (ctx.next != null){
            ctx = ctx.next;
        }
        ctx.next = new HandlerChainContext(node);
    }
}