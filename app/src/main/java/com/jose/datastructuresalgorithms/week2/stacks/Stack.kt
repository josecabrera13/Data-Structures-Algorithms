package com.jose.datastructuresalgorithms.week2.stacks

interface Stack<T> {
    fun push(item: T)
    fun pop(): T
    fun peek(): T
    fun isEmpty(): Boolean
}