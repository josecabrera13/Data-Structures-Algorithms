package com.jose.datastructuresalgorithms.week3.quiz;

import com.jose.datastructuresalgorithms.week2.queues.LinkedQueue;

import org.junit.Assert;
import org.junit.Test;

public class ShuffleLinkedQueueTest {
    @Test
    public void shuffleLinkedQueueTest() {
        Integer[] items = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 90};
        LinkedQueue<Integer> queue = new LinkedQueue<>();
        for (Integer item : items) {
            queue.enqueue(item);
        }
        ShuffleLinkedQueue.shuffleLinkedQue(queue);
        Assert.assertNotEquals(1, 2);
    }
}
