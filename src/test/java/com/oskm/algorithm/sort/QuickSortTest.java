/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm.sort;

import org.junit.Test;

public class QuickSortTest {

    private QuickSort quickSort = new QuickSort();

    @Test
    public void sort() {

        int[] result = quickSort.sort(new int[] {69, 10, 30, 2, 16, 8, 31, 22});

        for(int each : result) {
            System.out.print(" " + each);
        }
    }

}