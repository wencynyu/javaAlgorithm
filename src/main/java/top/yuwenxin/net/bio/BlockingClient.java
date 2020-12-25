package top.yuwenxin.net.bio;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class BlockingClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 8000);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("hello, " + socket.getRemoteSocketAddress() + "!! this is client data!!");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
