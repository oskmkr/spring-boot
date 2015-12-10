/*
 * Copyright (c) 2015. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oskm.algorithm.search;

import com.oskm.algorithm.sort.BubbleSort;
import com.oskm.algorithm.sort.Sort;
import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void search() {

        BinarySearch binarySearch = new BinarySearch();

        int[] list = {1, 2, 10, 7, 8, 9, 6, 3};
        int searchNumber = 2;

        Sort sort = new BubbleSort();

        int index = binarySearch.search(sort.sort(list), searchNumber);

        System.out.println("# searchNumber " + searchNumber + " is index : " + index);

    }

}