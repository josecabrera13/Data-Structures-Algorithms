package com.jose.datastructuresalgorithms.week2.stacks

import org.junit.Assert
import org.junit.Test

class StackTest {

    @Test
    fun testStackImplementations() {
        val stacksImplementations = arrayListOf<Stack<Int>>(LinkedStack(), ResizeArrayStack())
        for (stack in stacksImplementations) {
            pushAndPopAllItems(stack)
        }

    }

    private fun pushAndPopAllItems(stack: Stack<Int>) {
        val items = arrayListOf(1, 2, 3, 4, 5)
        for (item in items) {
            stack.push(item)
        }
        items.reverse()
        for (item in items) {
            Assert.assertEquals(item, stack.pop())
        }
    }
}