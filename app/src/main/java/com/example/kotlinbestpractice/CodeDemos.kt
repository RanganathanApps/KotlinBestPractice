package com.example.kotlinbestpractice

import com.example.kotlinbestpractice.movies.domain.model.Movie

fun main() {


    // while
    var counter = 0
    while( counter < 100) {
        println(counter)
        counter++
    }

    // do while
    var counter2 = 0
    do {
        println(counter2)
        counter2++
    } while ( counter2 != 100)

}

