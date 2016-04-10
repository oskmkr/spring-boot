package com.oskm.push;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by oskm on 2016-04-10.
 */
public class GCMMessageSenderTest {

    private GCMMessageSender uut = new GCMMessageSender();

    @Test
    public void send() {

        uut.send();
    }

}