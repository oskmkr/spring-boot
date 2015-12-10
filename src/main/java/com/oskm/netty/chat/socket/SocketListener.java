/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by sungkyu.eo on 2014-08-22.
 */
public class SocketListener extends Thread {

    private Socket socket;

    public SocketListener(Socket socket) {
        this.socket = socket;
        listen();
    }

    private void listen() {
        InputStream in = null;
        try {
            in = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            while (true) {
                String line = bufferedReader.readLine();

                if (null == line) {
                    break;
                }

                System.out.println(new String(line.getBytes(), "UTF-8"));
            }

            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
