package com.jose.datastructuresalgorithms.week3;

import org.junit.Assert;
import org.junit.Test;

public class MergeSortTest {
    @Test
    public void SortIntegersTest() {
        Integer[] items = {5,2,3,1};
        Integer[] itemsExpected = {1,2,3,5};
        MergeSort.sort(items,null);
        Assert.assertArrayEquals(itemsExpected, items);
    }
}
