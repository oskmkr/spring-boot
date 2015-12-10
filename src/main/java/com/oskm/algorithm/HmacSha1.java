/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by sungkyu.eo on 2014-09-30.
 */
public class HmacSha1 {

    public static final String HMAC_SHA_1 = "HmacSHA1";
    public static final String HMAC_SHA_256 = "HmacSHA256";

    public String encrypt(String data, String key) throws UnsupportedEncodingException {

        byte[] rawHmac = null;

        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(Charset.forName("UTF-8")), HMAC_SHA_1);

        Mac mac = null;
        try {
            mac = Mac.getInstance(HMAC_SHA_1);
            mac.init(signingKey);
            rawHmac = mac.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return base64(rawHmac);
    }

    public String base64(byte[] rawHmac) {
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
}
