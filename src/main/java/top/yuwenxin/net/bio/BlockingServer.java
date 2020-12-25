package top.yuwenxin.net.bio;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockingServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8000, 1024, InetAddress.getLocalHost());

            while (true){
                try {
                    System.out.println("server wait for accept...");
                    Socket accept = server.accept();
                    System.out.println("server accept success...");
                    DataInputStream in = new DataInputStream(accept.getInputStream());
                    System.out.println(in.readUTF());
                    DataOutputStream out = new DataOutputStream(accept.getOutputStream());
                    out.writeUTF("hello, " + accept.getRemoteSocketAddress() + "!! this is server data!!");
                    accept.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
