package top.yuwenxin.net.nio;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Buffers {
    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity){
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }

    public ByteBuffer gerWriteBuffer(){
        return writeBuffer;
    }

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        IntBuffer ib = bb.asIntBuffer();  // 构建整型缓冲区

        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016}); // 7 个数字
        System.out.println(ib.get(3)); // 输出 99

        ib.put(3, 1811);
        System.out.println(ib.get(3)); // 输出 1811

        ib.flip(); // 将缓冲区的limit属性设置为当前位置，并返回缓冲区的第一个位置；
//        ib.rewind(); // 返回缓冲区的第一个位置；
        System.out.println(ib.limit()); // 如果前面调用flip，则输出 7； 如果调用的是rewind，则输出256

        System.out.println("Output int buffer content: ");
        while (ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }
}
