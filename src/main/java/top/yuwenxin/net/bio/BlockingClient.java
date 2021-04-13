package top.yuwenxin.net.bio;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class BlockingClient {
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new ClientWorker());
            TimeUnit.SECONDS.sleep(1);
            threads[i].start();
        }
    }

    static class ClientWorker implements Runnable{
        @Override
        public void run() {
            try {
                Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
                doSomething();
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                doSomething();
                out.writeUTF("hello, " + socket.getRemoteSocketAddress() + "!! this is client data!!");
                doSomething();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println(in.readUTF());
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private void doSomething() throws InterruptedException {
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
