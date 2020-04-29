package com.jose.datastructuresalgorithms.week4;

//Priority queue is just an API, we use binary heap to implement a PQ
public class MaxPriorityQueue<Key extends Comparable<Key>> {

    private final BinaryHeap<Key> binaryHeap;

    public MaxPriorityQueue() {
        binaryHeap = new BinaryHeap<>();
    }

    public Key deleteMax() {
        return binaryHeap.deleteMax();
    }

    public void insert(Key item) {
        binaryHeap.insert(item);
    }
}
