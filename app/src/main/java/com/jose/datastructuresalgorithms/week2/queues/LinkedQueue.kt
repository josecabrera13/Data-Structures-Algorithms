package com.jose.datastructuresalgorithms.week2.queues

import com.jose.datastructuresalgorithms.week2.Node

open class LinkedQueue<T> : Queue<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null

    override fun enqueue(item: T) {
        val oldTail = tail
        tail = Node(item, null)
        if (isEmpty()) {
            head = tail
        } else {
            oldTail?.nextNode = tail
        }
    }

    override fun dequeue(): T {
        return head?.let {
            val item = it.item
            head = it.nextNode
            if (isEmpty()) {
                tail = null
            }
            item
        } ?: throw IllegalStateException()
    }

    override fun isEmpty(): Boolean = head == null
}