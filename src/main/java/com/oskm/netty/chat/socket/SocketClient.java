/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import java.io.IOException;
import java.net.Socket;

/**
 * http://pirate.shu.edu/~wachsmut/Teaching/CSAS2214/Virtual/Lectures/chat-client-server.html
 * Created by sungkyu.eo on 2014-08-22.
 */
public class SocketClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8023);

        Thread listen = new Thread(new SocketListener(socket));
        listen.start();

        Thread sender = new Thread(new SocketSender(socket));
        sender.start();

    }

}
