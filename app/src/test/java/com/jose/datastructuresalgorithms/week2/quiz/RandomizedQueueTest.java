package com.jose.datastructuresalgorithms.week2.quiz;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RandomizedQueueTest {

    private List<String> items = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
    private RandomizedQueue<String> queue = new RandomizedQueue<>();

    @Test
    public void dequeueAllElements() {
        for (String item : items) {
            queue.enqueue(item);
        }
        for (int i = 0; i < items.size(); i++) {
            int itemIndex = items.indexOf(queue.dequeue());
            Assert.assertTrue(itemIndex >= 0);
        }
        Assert.assertTrue(queue.isEmpty());
    }

}
