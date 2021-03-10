package com.andreivanceadev.sliidetechnical.util

class Optional<T>(var data: T? = null) {

    fun isEmpty() = data == null

    fun get(): T = data ?: throw NoSuchElementException("No value present")

}