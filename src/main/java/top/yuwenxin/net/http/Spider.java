package top.yuwenxin.net.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Spider {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        HttpClient.Builder builder = HttpClient.newBuilder();
        HttpClient client = builder.version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        HttpRequest request = HttpRequest.newBuilder(new URI("http://www.baidu.com"))
                .header("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.122 Safari/537.36")
                .version(HttpClient.Version.HTTP_1_1)
                .GET()
                .build();
        System.out.println(LocalDateTime.now());
        long t1 = System.currentTimeMillis();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        long t2 = System.currentTimeMillis();
        System.out.println(LocalDateTime.now());
        System.out.println("sync send cost: " + (t2 - t1) + "ms");
        String body = response.body();

        System.out.println(LocalDateTime.now());
        t1 = System.currentTimeMillis();
        CompletableFuture<HttpResponse<String>> future =
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        t2 = System.currentTimeMillis();
        System.out.println(LocalDateTime.now());
        System.out.println("async send cost: " + (t2 - t1) + "ms");


        System.out.println(LocalDateTime.now());
        t1 = System.currentTimeMillis();
        future.whenComplete(((stringHttpResponse, throwable) -> {
            System.out.println(LocalDateTime.now());
            String body1 = stringHttpResponse.body();
            System.out.println(body1);
        }));
        t2 = System.currentTimeMillis();
        System.out.println(LocalDateTime.now());
        System.out.println("async get body cost: " + (t2 - t1) + "ms");
        TimeUnit.SECONDS.sleep(3);  // 不sleep等待一下会导致异步响应来不及被处理主线程就die了

        System.out.println(body);
    }
}
