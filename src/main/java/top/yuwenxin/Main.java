package top.yuwenxin;

import io.vertx.core.Vertx;
import top.yuwenxin.net.vertx.HelloWorldVerticle;

public class Main {

    public static void main(String[] args) {
        // vertx异步http处理框架-2023.5.22
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(HelloWorldVerticle.class.getName());
    }
}
