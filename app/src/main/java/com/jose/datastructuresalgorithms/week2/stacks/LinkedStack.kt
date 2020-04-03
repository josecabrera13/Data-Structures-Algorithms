package com.jose.datastructuresalgorithms.week2.stacks

import com.jose.datastructuresalgorithms.week2.Node

class LinkedStack<T> : Stack<T> {

    private var head: Node<T>? = null

    override fun push(item: T) {
        head = Node(item, head)
    }

    override fun pop(): T {
        return head?.let {
            val item = it.item
            head = it.nextNode
            item
        } ?: throw IllegalStateException()
    }

    override fun isEmpty(): Boolean {
        return head == null
    }

    override fun peek(): T = head?.item ?: throw IllegalStateException()

}