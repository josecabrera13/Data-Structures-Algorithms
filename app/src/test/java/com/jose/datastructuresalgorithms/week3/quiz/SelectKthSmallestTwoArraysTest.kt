package com.jose.datastructuresalgorithms.week3.quiz

import org.junit.Assert
import org.junit.Test

class SelectKthSmallestTwoArraysTest {
    @Test
    fun selectKthSmallestTwoArray() {
        val arrayA = listOf(2, 4, 6, 8)
        val arrayB = listOf(1, 3, 5, 7, 9, 11)
        //1,2,3,4,5,6,7,8,9,11
        val expectedResult = 9
        val result =
            SelectKthSmallestTwoArrays.quickSelect(arrayA.toTypedArray(), arrayB.toTypedArray(), 8)
        Assert.assertEquals(expectedResult, result)
    }

}