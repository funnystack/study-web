package com.funny.study.springboot.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketTest {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8000);


        while (true){
            socket.setReuseAddress(true);
            Socket socket1 = socket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket1.getInputStream()));

            String clientInputStr = bufferedReader.readLine();
            System.out.println(clientInputStr);
        }

    }
}
