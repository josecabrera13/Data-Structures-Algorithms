package com.jose.datastructuresalgorithms.week3.quiz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CountInversionsArrayTest {
    private Map<Integer, Integer[]> dataSet;

    @Before
    public void init() {
        dataSet = new HashMap<>();
        dataSet.put(6, new Integer[]{8, 4, 2, 1});
        dataSet.put(0, new Integer[]{1, 2, 3, 4});
        dataSet.put(2, new Integer[]{3, 1, 2});
        dataSet.put(4, new Integer[]{8, 1, 20, 2, 19});
    }

    @Test
    public void countInversionTest() {
        for (Integer key : dataSet.keySet()) {
            Integer inversionsResult = CountInversionsArray.countInversions(dataSet.get(key));
            Assert.assertEquals(key, inversionsResult);
        }

    }
}
