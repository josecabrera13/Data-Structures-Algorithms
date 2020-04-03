package com.jose.datastructuresalgorithms.week2.queues

interface Queue<T> {
    fun enqueue(item: T)
    fun dequeue(): T
    fun isEmpty(): Boolean
}