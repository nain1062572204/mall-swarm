package com.wang.mall.admin.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(999));
        int port = 55533;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("等待连接....");
        while (true) {
            Socket socket = serverSocket.accept();
            //每有一个socket连接新建线程执行
            executor.submit(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                ) {
                    String str;
                    while ((str = reader.readLine()) != null) {
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
