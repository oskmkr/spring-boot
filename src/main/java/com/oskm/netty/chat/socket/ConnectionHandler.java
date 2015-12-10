/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

/**
 * Created by sungkyu.eo on 2014-08-25.
 */
public class ConnectionHandler implements Runnable {
    private Logger LOG = LoggerFactory.getLogger(ConnectionHandler.class);

    private Socket socket;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        while (true) {
            BufferedReader reader = null;
            BufferedWriter writer = null;
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String msg = reader.readLine();
                System.err.println(msg);

                Iterator<Socket> socketIterator = socketHolder.iterator();

                while (socketIterator.hasNext()) {

                    Socket each = socketIterator.next();

                    writer = new BufferedWriter(new OutputStreamWriter(each.getOutputStream()));

                    if (socket != each) {
                        writer.write("[" + each.getRemoteSocketAddress() + "] : " + msg + "\r\n");
                        writer.flush();
                    } else {
                        writer.write("[you] : " + msg + "\r\n");
                        writer.flush();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private SocketHolder socketHolder = new SocketHolder();
}
