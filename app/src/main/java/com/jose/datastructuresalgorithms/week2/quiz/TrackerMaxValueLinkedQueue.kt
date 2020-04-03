package com.jose.datastructuresalgorithms.week2.quiz

import com.jose.datastructuresalgorithms.week2.queues.LinkedQueue
import com.jose.datastructuresalgorithms.week2.stacks.LinkedStack

/*

 */
class TrackerMaxValueLinkedQueue : LinkedQueue<Int>() {
    private val maxValueTracker = LinkedStack<Int>()

    override fun enqueue(item: Int) {
        super.enqueue(item)
        if (maxValueTracker.isEmpty()) {
            maxValueTracker.push(item)
        } else {
            val stackItem = maxValueTracker.peek()
            if (item > stackItem) {
                maxValueTracker.push(item)
            } else {
                maxValueTracker.push(stackItem)
            }
        }
    }

    override fun dequeue(): Int {
        maxValueTracker.pop()
        return super.dequeue()
    }

    fun getMaxValue(): Int = maxValueTracker.peek()
}