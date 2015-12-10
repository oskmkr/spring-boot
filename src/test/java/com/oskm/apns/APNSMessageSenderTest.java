package com.oskm.apns;

import org.junit.Test;


public class APNSMessageSenderTest {

    private APNSMessageSender sender = new APNSMessageSender();

    @Test
    public void send() {
        sender.send();
    }

}
