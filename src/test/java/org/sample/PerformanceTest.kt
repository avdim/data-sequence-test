package org.sample

import jdk.nashorn.internal.ir.debug.JSONWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.sample.data.Person
import org.sample.data.RUSSIA
import org.sample.data.generateRandomData
import java.util.*
import kotlin.test.assertEquals

class PerformanceTest() {

    companion object {
        val DATA_SIZE = 100_000
    }

    @Test
    fun dataSize() {
        val bytes = generateRandomData(DATA_SIZE).toString().length
        val MB = bytes / 1E6
        println("data size: $MB MB")
    }

    @Test
    fun perfList() {
        val sequence = generateRandomData(DATA_SIZE)

        val result = calcAverageNanoDelay {
            sequence
                .sortedBy { it.weight }
                .take(1000)
        }

        result.data.forEach {
            println(it)
        }
    }

    @Test
    fun perfSequence() {
        val sequence = generateRandomData(DATA_SIZE).asSequence()

        val result = calcAverageNanoDelay {
            sequence
                .filter { it.country == RUSSIA }
                .filter { it.firstName.startsWith("Д") }
                .sortedBy { it.weight }// order
                .take(1000)
                .toList()
        }

        result.data.forEach {
            println(it)
        }
    }

    @Test
    fun testCoroutine() = runBlocking {

        val seq = generateRandomData(DATA_SIZE).asSequence()

        val result = calcAverageNanoDelay {
            seq
                .asFlow()
                .filter { it.country == RUSSIA }
                .filter { it.firstName.startsWith("Д") }
                .flowOn(Dispatchers.Default)
                .toList()
                .sortedBy { it.weight }
                .take(1000)

        }

        result.data.forEach {
            println(it)
        }

        Unit
    }

}

fun hardComparsion(a: Person, b: Person): Int {
    Thread.sleep(1)
    if (a.age != b.age) {
        return a.age.compareTo(b.age)
    } else {
        return a.weight.compareTo(b.weight)
    }
}
