package com.example.kotlinpractice

import java.util.Locale
import kotlin.math.max

fun largerNumber(a : Int, b : Int) = if (a > b) a else b

fun checknumber(num: Number) {
    when(num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("type not support")
    }
}

fun doStudy(study: Study) {
    study.doHomework()
    study.readBooks()
}

fun getTextLength(text: String?) = text?.length ?: 0


fun main() {
    println(getTextLength("hello"))
}