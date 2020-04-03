package com.jose.datastructuresalgorithms.week2.quiz;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node firstNode = null;
    private Node lastNode = null;
    private Integer itemsCounter = 0;

    public boolean isEmpty() {
        return firstNode == null;
    }

    public int size() {
        return itemsCounter;
    }

    public void addFirst(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        final Node oldFirst = firstNode;
        firstNode = new Node();
        firstNode.item = item;
        firstNode.nextRightNode = oldFirst;
        if (lastNode == null) {
            lastNode = firstNode;
        } else {
            oldFirst.nextLeftNode = firstNode;
        }
        itemsCounter++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (null == item) {
            throw new IllegalArgumentException();
        }
        final Node oldLastNode = lastNode;
        lastNode = new Node();
        lastNode.item = item;
        lastNode.nextLeftNode = oldLastNode;
        if (firstNode == null) {
            firstNode = lastNode;
        } else {
            oldLastNode.nextRightNode = lastNode;
        }
        itemsCounter++;

    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = firstNode.item;
        firstNode = firstNode.nextRightNode;
        if (firstNode == null) {
            lastNode = null;
        }
        itemsCounter--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = lastNode.item;
        lastNode = lastNode.nextLeftNode;
        if (lastNode == null) {
            firstNode = null;
        }
        itemsCounter--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    class Node {
        Item item;
        Node nextLeftNode;
        Node nextRightNode;
    }

    private class ArrayIterator implements Iterator<Item> {
        private Integer iteratorCounter = 0;
        private Node iteratorNode = firstNode;

        @Override
        public boolean hasNext() {
            return iteratorCounter < itemsCounter;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = iteratorNode.item;
            iteratorNode = iteratorNode.nextRightNode;
            iteratorCounter++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }
}
