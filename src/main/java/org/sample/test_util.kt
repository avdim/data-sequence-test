package org.sample

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
public inline fun calcAverageNanoDelay(name:String, count:Int, block: () -> Unit): Long {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    val start = System.nanoTime()
    repeat(count) {
        block()
    }
    val resultNanos = (System.nanoTime() - start) / count
    println("---------------------------")
    println("$name, ms: ${resultNanos / 1E6}")
    println("---------------------------")
    return resultNanos
}
