package com.jose.datastructuresalgorithms.week2.stacks

class ResizeArrayStack<T> : Stack<T> {
    private var arrayStack = arrayOfNulls<Any>(1)
    private var n: Int = 0

    override fun push(item: T) {
        if (arrayStack.size == n) {
            resizeArray(arrayStack.size * 2)
        }
        arrayStack[n++] = item
    }

    // We can't avoid the unchecked, we can't create generics arrays.
    override fun pop(): T {
        val item = arrayStack[--n]
        arrayStack[n] = null
        if (n > 0 && n == arrayStack.size / 4) {
            resizeArray(arrayStack.size / 2)
        }
        return item as T
    }

    override fun isEmpty(): Boolean = n == 0

    private fun resizeArray(newArraySize: Int) {
        val newArrayStack = arrayOfNulls<Any>(newArraySize)
        for (index in 0 until n) {
            newArrayStack[index] = arrayStack[index]
        }
        arrayStack = newArrayStack
    }

    override fun peek(): T = arrayStack[n - 1] as T
}