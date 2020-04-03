package com.jose.datastructuresalgorithms.week2.quiz

import org.junit.Assert
import org.junit.Test

class TrackerMaxValueLinkedQueueTest {
    private val items = listOf(1, 15, 5, 3, 0, 90, 10, 9, 30)
    private val maxValuesExpected = listOf(90, 90, 90, 90, 15, 15, 15, 15, 1)

    @Test
    fun testMaxValuesQueue() {
        val queue = TrackerMaxValueLinkedQueue()
        for (item in items) {
            queue.enqueue(item)
        }
        for (maxValueExpected in maxValuesExpected) {
            Assert.assertEquals(maxValueExpected, queue.getMaxValue())
            queue.dequeue()
        }
    }
}