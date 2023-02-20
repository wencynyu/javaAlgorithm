package top.yuwenxin.net.netty.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class NettyServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors() * 2);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(8000))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyServerHandler());
                    }
                });
            ChannelFuture future = bootstrap.bind().sync();
            future.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                bossGroup.shutdownGracefully().sync();
                workerGroup.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

// 继承绑定channel的处理适配器，netty自带了http的处理器可以继承后实现特殊需求
class NettyServerHandler extends ChannelInboundHandlerAdapter{
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
        ByteBuf receivePkg = (ByteBuf) msg;
        System.out.println(receivePkg);
        String receiveData = receivePkg.toString(StandardCharsets.UTF_8);
        System.out.println(receiveData);
        ctx.writeAndFlush(Unpooled.copiedBuffer("[server] receive data: " + receiveData + "\n", StandardCharsets.UTF_8));
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("server active...tcp channel has been established.");
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println(cause.getMessage());
        super.exceptionCaught(ctx, cause);
    }
}
