/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm.search;

import java.util.Arrays;

/**
 * @reference http://blog.eairship.kr/246
 * Created by sungkyu.eo on 2015-03-25.
 */
public class BinarySearch {

    public int search(final int[] list, final int findNumber) {

        int[] temp = Arrays.copyOf(list, list.length);
        int low = 0, high = list.length - 1, mid;

        while (low <= high) {

            mid = (low + high) / 2;

            if (temp[mid] > findNumber) {
                high = mid - 1;
            } else if (temp[mid] < findNumber) {
                low = mid + 1;
            } else {
                return mid;
            }

        }

        return -1;

    }

}
