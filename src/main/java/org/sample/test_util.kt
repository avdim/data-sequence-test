package org.sample

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
public inline fun calcAverageNanoDelay(count:Int, block: () -> Unit): Long {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    val start = System.nanoTime()
    repeat(count) {
        block()
    }
    return (System.nanoTime() - start) / count
}

