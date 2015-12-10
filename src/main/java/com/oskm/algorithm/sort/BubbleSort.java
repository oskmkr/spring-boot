/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm.sort;

import java.util.Arrays;

/**
 * @reference http://www.jynote.net/490
 * Created by sungkyu.eo on 2015-03-23.
 */
public class BubbleSort implements Sort {


    @Override
    public int[] sort(final int[] list) {

        int temp[] = Arrays.copyOf(list, list.length);

        int listLength = temp.length, hold;

        for (int i = 0; i < listLength; i++) {
            for (int j = 0; j < listLength - 1; j++) {
                if (temp[j] > temp[j + 1]) {
                    hold = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = hold;
                }
            }
        }

        return temp;
    }

}
