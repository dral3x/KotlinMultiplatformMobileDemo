package com.spreaker.kmm.shared.domain


@OptIn(ExperimentalUnsignedTypes::class)
fun calculateFactorial(number: Int): ULong {
    var factorial: ULong = 1UL
    for (i in 1..number) {
        factorial = factorial * i.toULong()
    }
    return factorial
}

fun performCPUIntensiveTask() {
    for (i in 1..150) {
        calculateFactorial(5000000)
    }
}