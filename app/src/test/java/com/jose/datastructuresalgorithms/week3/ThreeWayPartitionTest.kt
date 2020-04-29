package com.jose.datastructuresalgorithms.week3

import org.junit.Assert
import org.junit.Test

class ThreeWayPartitionTest {
    @Test
    fun threeWayPartitionTest() {
        val data = listOf(1, 14, 15, 3, 4, 3, 6, 7, 8, 9, 3, 11, 12, 13, 1, 1, 16, 17, 18, 19).toTypedArray()
        ThreeWayPartition.sort(data)
        Assert.assertEquals(1, ThreeWayPartition.getDecimalDominant().first())
    }
}