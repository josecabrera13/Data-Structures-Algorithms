package com.jose.datastructuresalgorithms.week3;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {
    @Test
    public void SortIntegersTest() {
        Integer[] items = {9, 6, 8, 1, 2};
        Integer[] itemsExpected = {1, 2, 6, 8, 9};
        MergeSort.sort(items, null);
        Assert.assertArrayEquals(itemsExpected, items);
    }
}
