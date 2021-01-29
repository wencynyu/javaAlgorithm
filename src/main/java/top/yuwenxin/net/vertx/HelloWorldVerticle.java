package top.yuwenxin.net.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

/**
 * @Author: wenxin.yu
 * @Date: 2021/1/29 16:41
 */
public class HelloWorldVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);

        Route route1 = router.route("/hello1").handler(routingContext -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // 写入响应并结束处理
            response.end("Hello World from Vert.x-Web!1");
        });

        Route route2 = router.route("/hello2").handler(routingContext -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // 写入响应并结束处理
            response.end("Hello World from Vert.x-Web!2");
        });

        Route route3 = router.route("/hello3").method(HttpMethod.GET)
                .handler(routingContext -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");

            // 写入响应并结束处理
            response.end("Hello World from Vert.x-Web!3");
        });


        vertx.createHttpServer().requestHandler(router).listen(8080);
    }
}
