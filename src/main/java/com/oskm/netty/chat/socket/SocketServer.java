/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by sungkyu.eo on 2014-08-25.
 */
public class SocketServer {
    private static final Logger LOG = Logger.getLogger(SocketServer.class);

    private static final int PORT = 8023;

    public static void main(String args[]) {

        LOG.debug("server started...");

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            Thread t = new Thread(new ServerHandler(serverSocket));
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

