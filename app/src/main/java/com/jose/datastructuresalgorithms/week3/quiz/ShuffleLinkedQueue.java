package com.jose.datastructuresalgorithms.week3.quiz;

import com.jose.datastructuresalgorithms.week2.Node;
import com.jose.datastructuresalgorithms.week2.queues.LinkedQueue;

import java.util.Random;

public class ShuffleLinkedQueue {

    private ShuffleLinkedQueue() {

    }

    public static <T> void shuffleLinkedQue(final LinkedQueue<T> queue) {
        int numberItems = queue.getItemCounter();
        int midPoint = numberItems / 2;
        Node<T> copyHead = queue.getHead();
        Node<T> midNode = queue.getHead();
        for (int i = 0; i < midPoint; i++) {
            midNode = midNode.getNextNode();
        }

        Random random = new Random();
        for (int i = 0; i < midPoint; i++) {
            if (random.nextBoolean()) {
                T memoryItem = midNode.getItem();
                midNode.setItem(copyHead.getItem());
                copyHead.setItem(memoryItem);
            }
            copyHead = copyHead.getNextNode();
            midNode = midNode.getNextNode();


        }

    }

}
