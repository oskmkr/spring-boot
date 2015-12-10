/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by sungkyu.eo on 2014-08-22.
 */
public class SocketSender implements Runnable {
    public SocketSender(Socket socket) {
        this.socket = socket;

    }

    private Socket socket;

    @Override
    public void run() {
        System.out.println("listen completed..");
        try {
            OutputStream out = socket.getOutputStream();

            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter outWriter = new BufferedWriter(new OutputStreamWriter(out));

            while (true) {
                System.out.println("msg input : ");
                String line = console.readLine();

                if ("bye".equals(line)) {
                    break;
                }

                outWriter.write(line + "\r\n");
                outWriter.flush();
            }

            outWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
