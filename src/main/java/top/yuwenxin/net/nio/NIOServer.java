package top.yuwenxin.net.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) {
        Charset charset = Charset.forName("utf-8");
        InetSocketAddress address = null;
        Selector selector = null;
        ServerSocketChannel channel = null;
        try {
            address = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
            selector = Selector.open();
            channel = ServerSocketChannel.open();
            // 创建一个socketChannel，并置为非阻塞，然后将该channel与端口绑定
            channel.configureBlocking(false);
            channel.bind(address, 1024);

            // 最后将该channel与selector进行注册，监听端口可注册为关注accept操作
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int n = selector.select();
                if (n == 0){
                    continue;
                }

                // 遍历selector的全部关注key，对不同key进行分别处理
                Set<SelectionKey> keySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keySet.iterator();
                SelectionKey key = null;

                while (iterator.hasNext()) {
                    key = iterator.next();
                    // 遍历完成后从selector的选择set中移除，避免重复处理同一个channel
                    iterator.remove();

                    try {
                        // 当前key为可accept，也就是有其他client通过socket进行连接
                        if (key.isAcceptable()){
                            // accept方法会返回一个普通通道，每个通道在内核中都对应一个socket缓冲区
                            SocketChannel sc = channel.accept();
                            sc.configureBlocking(false);
                            // 向selector注册这个通道为可读（建立连接后首先要读取client的信息），同时提供这个新通道相关的缓冲区
                            sc.register(selector, SelectionKey.OP_READ, new Buffers(256, 256));

                            System.out.println("accept from: " + sc.getRemoteAddress());
                        }

                        // 当前key为可read，
                        if (key.isReadable()) {
                            // 通过附件形式获取当前可读通道的Buffer区
                            Buffers attachment = (Buffers)key.attachment();
                            ByteBuffer readBuffer = attachment.getReadBuffer();
                            ByteBuffer writeBuffer = attachment.gerWriteBuffer();

                            // 获取当前可读通道
                            SocketChannel sc = (SocketChannel) key.channel();

                            // 从读缓冲区读入数据
                            sc.read(readBuffer);
                            readBuffer.flip();

                            // utf-8解码显示字符数据
                            CharBuffer charBuffer = charset.decode(readBuffer);
                            System.out.println(charBuffer.array());

                            readBuffer.rewind();

                            // 读完数据后根据请求内容返回服务端数据
                            writeBuffer.put("server response: ".getBytes("utf-8"));
                            writeBuffer.put(readBuffer);
                            readBuffer.clear();

                            // 操作+r，通过按位或来进行修改
                            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                        }

                        // 当前key为可write
                        if (key.isWritable()) {
                            Buffers  buffers = (Buffers)key.attachment();

                            ByteBuffer writeBuffer = buffers.gerWriteBuffer();
                            writeBuffer.flip();

                            SocketChannel sc = (SocketChannel) key.channel();

                            int len = 0;
                            while(writeBuffer.hasRemaining()){
                                len = sc.write(writeBuffer);
                                /*说明底层的socket写缓冲已满*/
                                if(len == 0){
                                    break;
                                }
                            }

                            writeBuffer.compact();

                            /*说明数据全部写入到底层的socket写缓冲区*/
                            if(len != 0){
                                /*取消通道的写事件*/
                                key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                            }
                        }
                    }catch (Exception e){
                        System.out.println("service encounter client error");
                        // 客户端连接出现异常，从selector中移除这个key
                        key.cancel();
                        key.channel().close();
                    }

                }
            }
        }catch (Exception e){
            System.out.println("serverThread is interrupted");
        }finally {
            try{
                selector.close();
            }catch(Exception e){
                System.out.println("selector close failed");
            }finally{
                System.out.println("server close");
            }
        }

    }
}
