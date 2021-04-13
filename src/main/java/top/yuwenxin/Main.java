package top.yuwenxin;

import io.vertx.core.Vertx;
import top.yuwenxin.net.vertx.HelloWorldVerticle;

import java.util.*;

public class Main {
    private static final int _1M = 1024 * 1024;
    public static void main(String[] args) {
        // 梦开始的地方😄
        System.out.println("hello world");

        // vertx异步http处理框架
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(HelloWorldVerticle.class.getName());

    }
}
