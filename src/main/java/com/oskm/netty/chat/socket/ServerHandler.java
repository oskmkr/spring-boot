/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sungkyu.eo on 2014-08-25.
 */
public class ServerHandler implements Runnable {
    //private Thread t;

    public ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Socket socket = serverSocket.accept();

                socketHolder.add(socket);

                BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                bWriter.write("welcome...\r\n");
                bWriter.flush();

                Thread t = new Thread(new ConnectionHandler(socket));
                t.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ServerSocket serverSocket;
    private SocketHolder socketHolder = new SocketHolder();
}
