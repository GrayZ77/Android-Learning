package com.example.kotlinpractice

class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age), Study {

    override fun readBooks() {
        println("$name is reading books")
    }
}