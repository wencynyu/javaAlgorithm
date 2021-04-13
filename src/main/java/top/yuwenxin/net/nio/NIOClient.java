package top.yuwenxin.net.nio;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {
    public static void main(String[] args) {
        Thread[] threads = new Thread[1];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                Charset charset = Charset.forName("utf-8");
                InetSocketAddress serverAddress = null;
                Selector selector = null;
                SocketChannel channel = null;

                try {
                    serverAddress = new InetSocketAddress(InetAddress.getLocalHost(), 8000);
                    selector = Selector.open();
                    channel = SocketChannel.open();
                    channel.configureBlocking(false);

                    channel.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, new Buffers(256, 256));

                    channel.connect(serverAddress);
                    while (!channel.finishConnect()) {
                    }
                    System.out.println("当前client完成tcp连接: from " + channel.getLocalAddress() + " to " + channel.getRemoteAddress());

                    for (int t = 0; t < 10; t++) {
                        int n = selector.select();
                        if (n == 0) {
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

                            Buffers attachment = (Buffers)key.attachment();
                            ByteBuffer readBuffer = attachment.getReadBuffer();
                            ByteBuffer writeBuffer = attachment.gerWriteBuffer();
                            SocketChannel sc = (SocketChannel) key.channel();

                            try {
                                // 当前key为可read，
                                if (key.isReadable()) {
                                    // 从读缓冲区读入数据
                                    sc.read(readBuffer);
                                    readBuffer.flip();

                                    // utf-8解码显示字符数据
                                    CharBuffer charBuffer = charset.decode(readBuffer);
                                    System.out.println(charBuffer.array());

                                    readBuffer.clear();
                                }

                                // 当前key为可write
                                if (key.isWritable()) {
                                    writeBuffer.put(("hello server, now is " + t + "\n").getBytes("utf-8"));
                                    writeBuffer.flip();
                                    sc.write(writeBuffer);
                                    writeBuffer.clear();
                                }
                            }catch (Exception e){
                                System.out.println("service encounter server error");
                                // 服务端连接出现异常，从selector中移除这个key
                                key.cancel();
                                key.channel().close();
                            }
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
    }
}
