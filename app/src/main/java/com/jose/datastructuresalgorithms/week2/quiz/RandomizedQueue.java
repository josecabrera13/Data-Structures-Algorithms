package com.jose.datastructuresalgorithms.week2.quiz;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

class RandomizedQueue<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int itemCounter = 0;

    public int size() {
        return itemCounter;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.nextNodeRight = oldTail;
        if (head == null) {
            head = tail;
        } else {
            oldTail.nextNodeLeft = tail;
        }
        itemCounter++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomNodePosition = generateRandomNumber();
        Item item;
        Node auxiliaryHead = head;
        for (int i = 0; i < randomNodePosition; i++) {
            auxiliaryHead = auxiliaryHead.nextNodeLeft;
        }
        item = auxiliaryHead.item;
        Node rightNode = auxiliaryHead.nextNodeRight;
        Node leftNode = auxiliaryHead.nextNodeLeft;
        if (leftNode != null) {
            leftNode.nextNodeRight = rightNode;
            if (leftNode.nextNodeRight == null) {
                head = leftNode;
            }
        }
        if (rightNode != null) {
            rightNode.nextNodeLeft = leftNode;
            if (rightNode.nextNodeLeft == null) {
                tail = rightNode;
            }
        }
        if (rightNode == null && leftNode == null) {
            head = null;
            tail = null;
        }
        itemCounter--;
        return item;
    }


    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int randomNodePosition = generateRandomNumber();
        Item item;
        Node auxiliaryHead = head;
        for (int i = 0; i < randomNodePosition; i++) {
            auxiliaryHead = auxiliaryHead.nextNodeLeft;
        }
        return item = auxiliaryHead.item;
    }

    private int generateRandomNumber() {
        int bound = itemCounter - 1;
        if (bound == 0) {
            return 0;
        }
        Random random = new Random();
        return random.nextInt(itemCounter - 1);
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class Node {
        Item item;
        Node nextNodeLeft;
        Node nextNodeRight;
    }

    private class ArrayIterator implements Iterator<Item> {
        private Integer iteratorCounter = 0;
        private Node iteratorNode = head;

        @Override
        public boolean hasNext() {
            return iteratorCounter < itemCounter;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = iteratorNode.item;
            iteratorNode = iteratorNode.nextNodeLeft;
            iteratorCounter++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }
    }
}
