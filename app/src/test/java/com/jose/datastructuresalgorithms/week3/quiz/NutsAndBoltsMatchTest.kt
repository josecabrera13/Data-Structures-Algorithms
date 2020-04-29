package com.jose.datastructuresalgorithms.week3.quiz

import org.junit.Assert
import org.junit.Test

class NutsAndBoltsMatchTest {
    @Test
    fun matchNutsAndBolts() {
        val nuts = mutableListOf(8, 6, 5, 10).toTypedArray()
        val bolts = mutableListOf(5, 8, 10, 6).toTypedArray()
        NutsAndBoltsMatch.matchArray(nuts, bolts)
        Assert.assertArrayEquals(nuts, bolts)
    }
}