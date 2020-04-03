package com.jose.datastructuresalgorithms.week2.quiz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DequeTest {

    private List<Integer> itemsFirst;
    private List<Integer> itemsLast;
    private Deque<Integer> deque;

    @Before
    public void setUp() {
        deque = new Deque<>();
        itemsFirst = new ArrayList<>(Arrays.asList(1, 2, 3, 78, 9, 3, 88, 0, 56));
        itemsLast = new ArrayList<>(Arrays.asList(100, 200, 300, 400));
    }

    @Test
    public void addAllFirstRemoveAllLast() {
        for (Integer item : itemsFirst) {
            deque.addFirst(item);
        }
        for (Integer item : itemsFirst) {
            Assert.assertEquals(item, deque.removeLast());
        }
    }

    @Test
    public void addAllFirstRemoveAllFirst() {
        final Deque<Integer> deque = new Deque<>();
        for (Integer item : itemsFirst) {
            deque.addFirst(item);
        }
        Collections.reverse(itemsFirst);
        for (Integer item : itemsFirst) {
            Assert.assertEquals(item, deque.removeFirst());
        }
    }

    @Test
    public void addAllFirstAddAllLastRemoveAllLast() {
        for (Integer item : itemsFirst) {
            deque.addFirst(item);
        }
        for (Integer item : itemsLast) {
            deque.addLast(item);
        }
        Collections.reverse(itemsLast);
        itemsLast.addAll(itemsFirst);
        for (Integer item : itemsLast) {
            Assert.assertEquals(item, deque.removeLast());
        }
    }

    @Test
    public void iteratorDeque() {
        final Deque<Integer> deque = new Deque<>();
        for (Integer item : itemsFirst) {
            deque.addFirst(item);
        }
        Collections.reverse(itemsFirst);

        int i = 0;
        for (Integer item : deque) {
            Assert.assertEquals(itemsFirst.get(i), item);
            i++;
        }

    }
}
