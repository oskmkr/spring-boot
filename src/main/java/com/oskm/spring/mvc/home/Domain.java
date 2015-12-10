/*
 * Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.spring.mvc.home;

/**
 * Created by sungkyu.eo on 2014-12-18.
 */
public class Domain {

    private static Domain INSTANCE;

    private Domain() {

    }

    private static Domain getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new Domain();
        }

        return INSTANCE;
    }
}
