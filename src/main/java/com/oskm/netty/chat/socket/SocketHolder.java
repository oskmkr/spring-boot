/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.netty.chat.socket;

import java.net.Socket;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by sungkyu.eo on 2014-09-04.
 */
public class SocketHolder {

    //private BlockingQueue<Socket> socketQueue = new ArrayBlockingQueue<Socket>(100);

    private static Queue<Socket> socketQueue = new ConcurrentLinkedQueue<Socket>();

    public void add(Socket socket) {
        socketQueue.add(socket);
    }

    public void remove(Socket socket) {
        socketQueue.remove(socket);
    }

    public Iterator<Socket> iterator() {
        return socketQueue.iterator();
    }
}
