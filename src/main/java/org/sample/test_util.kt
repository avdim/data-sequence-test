package org.sample

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
public inline fun <T> calcAverageNanoDelay(count: Int = 5, block: () -> T): MeasureResult<T> {
    contract {
        callsInPlace(block, InvocationKind.AT_LEAST_ONCE)
    }
    val start = System.nanoTime()
    var result: T? = null
    repeat(count) {
        result = block()
    }
    val resultNanos = (System.nanoTime() - start) / count
    val timeMs = resultNanos / 1E6
    return MeasureResult(result!!, timeMs)
}

data class MeasureResult<T>(val data: T, val timeMs: Double) {
    val countInSec = 1000 / timeMs
}
