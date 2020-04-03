package com.jose.datastructuresalgorithms.week2.queues

class ResizeArrayQueue<T> : Queue<T> {

    private var itemCounter: Int = 0
    private var indexHead: Int = 0
    private var indexTail: Int = 0
    private var arrayQueue = arrayOfNulls<Any>(1)

    override fun enqueue(item: T) {
        if (arrayQueue.size == itemCounter) {
            resize(arrayQueue.size * 2)
        }
        arrayQueue[indexTail++] = item
        itemCounter++

    }

    override fun dequeue(): T {
        val item = arrayQueue[indexHead]
        arrayQueue[indexHead] = null
        indexHead++
        itemCounter--
        if (itemCounter > 0 && itemCounter == arrayQueue.size / 4) {
            resize(arrayQueue.size / 2)
        }
        return item as T
    }

    override fun isEmpty(): Boolean = arrayQueue[indexHead] == null

    private fun resize(newArrayQueueSize: Int) {
        val newArrayQueue = arrayOfNulls<Any>(newArrayQueueSize)
        for (index in indexHead until indexTail) {
            newArrayQueue[index - indexHead] = arrayQueue[index]
        }
        arrayQueue = newArrayQueue
        indexTail -= indexHead
        indexHead = 0

    }

}