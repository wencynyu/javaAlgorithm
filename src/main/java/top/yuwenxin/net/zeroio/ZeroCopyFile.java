package top.yuwenxin.net.zeroio;

import java.io.*;
import java.nio.channels.FileChannel;

public class ZeroCopyFile {

    public static void main(String[] args) {
        try {
            String path = ZeroCopyFile.class.getClassLoader().getResource("").getPath();
            FileChannel fc1 = new RandomAccessFile(path + "origin.txt", "r").getChannel();
            FileChannel fc2 = new RandomAccessFile(path + "copy.txt", "rw").getChannel();

            long t1 = System.currentTimeMillis();
            fc1.transferTo(0, fc1.size(), fc2);
            long t2 = System.currentTimeMillis();
            System.out.println("zero copy cost: " + (t2 - t1) + "ms");

            File f1 = new File(path + "origin.txt");
            File f2 = new File(path + "copy2.txt");
            File f3 = new File(path + "copy3.txt");
            BufferedInputStream bufIn = new BufferedInputStream(new FileInputStream(f1));
            BufferedOutputStream bufOut = new BufferedOutputStream(new FileOutputStream(f2));
            int length = (int) f1.length();
            System.out.println((length >> 8));
            t1 = System.currentTimeMillis();
            byte[] buffer = new byte[4096 * 30];
            int f = 0;
            while ((f = bufIn.read(buffer)) != -1){
                bufOut.write(buffer, 0, f);
                bufOut.flush();
            }
            t2 = System.currentTimeMillis();
            System.out.println("buffered stream copy cost: " + (t2 - t1) + "ms");
            bufIn.close();
            bufOut.close();

            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f3);
            t1 = System.currentTimeMillis();
            int r = 0;
            byte[] buffer2 = new byte[(length >> 8)];
            while ((r = in.read(buffer2)) != -1){
                out.write(buffer2, 0, r);
            }
            t2 = System.currentTimeMillis();
            System.out.println("traditional stream copy cost: " + (t2 - t1) + "ms");
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
