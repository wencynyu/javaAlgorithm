package top.yuwenxin.design.action;

import java.util.ArrayList;
import java.util.List;

/**
 * 被观察者为主体，需要被client进行订阅（attach/subscribe），订阅方法在主体中实现，具体表现为内部容器中添加新订阅的client观察者
 * 观察者为client，订阅相关的主体后，每当主体进行改变，client都会收到通知
 */
public class ObserverPattern {
    public static void main(String[] args) {
        Subject subject = new Subject();

        // 构造方法中执行了订阅attach方法与subject绑定
        new HexObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}

class Subject {

    private List<Observer> observers
            = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

// 观察者抽象类，内部保存了一个主体对象
abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

// 三种client实现观察者对象
class BinaryObserver extends Observer {

    // 在构造方法中直接订阅：attach
    public BinaryObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Binary String: "
                + Integer.toBinaryString( subject.getState() ) );
    }
}

class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Octal String: "
                + Integer.toOctalString( subject.getState() ) );
    }
}

class HexObserver extends Observer {

    public HexObserver(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println( "Hex String: "
                + Integer.toHexString( subject.getState() ).toUpperCase() );
    }
}