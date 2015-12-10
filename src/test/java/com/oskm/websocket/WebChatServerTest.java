package com.oskm.websocket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class WebChatServerTest {

    @Test
    public void run() throws Exception {
        int port = 8886;
        WebChatServer server = new WebChatServer(port);
        server.start();
        System.out.printf("started... port %d", server.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String in = sysin.readLine();
            server.sendToAll(in);
            if (in.equals("exit")) {
                server.stop();
                break;
            } else if (in.equals("restart")) {
                server.stop();
                server.start();
                break;
            }
        }
    }
}
