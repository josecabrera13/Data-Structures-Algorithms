package com.jose.datastructuresalgorithms.week2.queues

import com.jose.datastructuresalgorithms.week2.quiz.TwoStacksQueue
import org.junit.Assert
import org.junit.Test

class QueueTest {
    @Test
    fun testAllQueueImplementations() {
        val queueImplementations = arrayListOf<Queue<Int>>(LinkedQueue(), ResizeArrayQueue(), TwoStacksQueue())
        for (queue in queueImplementations) {
            pushAndPopAllItems(queue)
        }
    }

    private fun pushAndPopAllItems(queue: Queue<Int>) {
        val items = arrayListOf(1, 2, 3, 4, 5)
        for (item in items) {
            queue.enqueue(item)
        }
        for (item in items) {
            Assert.assertEquals(item, queue.dequeue())
        }
    }
}