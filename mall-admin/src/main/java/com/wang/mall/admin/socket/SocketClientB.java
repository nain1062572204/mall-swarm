package com.wang.mall.admin.socket;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClientB {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 55533;
        Socket socket = new Socket(host, port);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        String str;
        while (true) {
            str = "B发送：" + reader.readLine();
            writer.write(str);
            System.out.println(str);
            writer.write("\n");
            writer.flush();
        }
    }
}
