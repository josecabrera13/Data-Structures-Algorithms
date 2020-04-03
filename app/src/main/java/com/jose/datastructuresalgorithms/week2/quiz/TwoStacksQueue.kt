package com.jose.datastructuresalgorithms.week2.quiz

import com.jose.datastructuresalgorithms.week2.queues.Queue
import com.jose.datastructuresalgorithms.week2.stacks.LinkedStack

class TwoStacksQueue<T> : Queue<T> {

    private val firstStack = LinkedStack<T>()
    private val secondStack = LinkedStack<T>()

    override fun enqueue(item: T) {
        firstStack.push(item)
    }

    override fun dequeue(): T {
        if (secondStack.isEmpty()) {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop())
            }
        }
        return secondStack.pop()
    }

    override fun isEmpty(): Boolean = firstStack.isEmpty() && secondStack.isEmpty()
}