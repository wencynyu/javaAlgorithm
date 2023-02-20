package top.yuwenxin.net.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class NettyClient {
    public static final int UDP_CLIENT_PORT = 50001;
    public static void main(String[] args) {
        Thread[] threads = new Thread[1];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                EventLoopGroup group = new NioEventLoopGroup();
                try {
                    Bootstrap bootstrap = new Bootstrap();
                    bootstrap.group(group).channel(NioDatagramChannel.class)
                        .remoteAddress(new InetSocketAddress("127.0.0.1", 8000))
                        .handler(new ChannelInitializer<DatagramChannel>() {
                            @Override protected void initChannel(DatagramChannel socketChannel) throws Exception {
                                socketChannel.pipeline().addLast(new NettyClientHandler());
                            }
                        });
                    while (true){
                        ChannelFuture future = bootstrap.connect().sync();
                        future.channel().closeFuture().sync();
                    }
//                    ChannelFuture future = bootstrap.connect().sync();
//                    future.channel().closeFuture().sync();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        group.shutdownGracefully().sync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            threads[i].start();
        }
    }
}

class NettyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf)
        throws Exception {
        System.out.println("client read...");
        System.out.println("client receive data: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client active...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty server!! i'm " + Thread.currentThread().getName(), CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client error...");
        cause.printStackTrace();
        ctx.close();
    }
}
