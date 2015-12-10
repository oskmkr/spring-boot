/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

/**
 * Created by sungkyu.eo on 2014-09-30.
 */
public class Base64 {

    public static String encode(byte[] rawHmac) throws UnsupportedEncodingException {
        byte[] buf = null;
        try {
            Class Base64 = Class.forName("org.apache.commons.codec.binary.Base64");
            Class[] parameterTypes = new Class[]{byte[].class};
            Method encodeBase64 = Base64.getMethod("encodeBase64", parameterTypes);
            buf = (byte[]) encodeBase64.invoke(Base64, rawHmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(buf);

    }

    public static String decode(byte[] rawHmac) throws UnsupportedEncodingException {
        byte[] buf = null;
        try {
            Class Base64 = Class.forName("org.apache.commons.codec.binary.Base64");
            Class[] parameterTypes = new Class[]{byte[].class};
            Method decodeBase64 = Base64.getMethod("decodeBase64", parameterTypes);
            buf = (byte[]) decodeBase64.invoke(Base64, rawHmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(buf, "UTF-8");

    }
}
