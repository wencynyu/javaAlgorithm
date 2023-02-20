package top.yuwenxin.net.netty.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NettyServer {
    private static final InetSocketAddress BROADCAST_ADDRESS = new InetSocketAddress("255.255.255.255", NettyClient.UDP_CLIENT_PORT);

    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioDatagramChannel.class)
                    .localAddress(new InetSocketAddress("127.0.0.1", 8000))
                    .option(ChannelOption.SO_BROADCAST, true)
                    .option(ChannelOption.SO_RCVBUF, 2048 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 1024 * 1024)
                    .handler(new UdpServerHandler());
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                workerGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class UdpServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server registered...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("server registered...", StandardCharsets.UTF_8));
        super.channelRegistered(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server inactive...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("server inactive...", StandardCharsets.UTF_8));
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server read complete...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("server read complete...", StandardCharsets.UTF_8));
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server unregistered...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("server unregistered...", StandardCharsets.UTF_8));
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server write changed...");
        ctx.writeAndFlush(Unpooled.copiedBuffer("server write changed...", StandardCharsets.UTF_8));
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server read...");
        DatagramPacket receivePkg = (DatagramPacket) msg;
        System.out.println(receivePkg);
        String receiveData = receivePkg.content().toString(StandardCharsets.UTF_8);
        System.out.println(receiveData);
        ctx.writeAndFlush(Unpooled.copiedBuffer("[server] receive data: " + receiveData + "\n", StandardCharsets.UTF_8));
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server active...upd channel has been established.");
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}