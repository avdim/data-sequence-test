package org.sample

import org.junit.Test
import org.sample.data.RUSSIA
import org.sample.data.generateRandomData
import org.sample.data.toJsonStr

class PerformanceTest() {

    companion object {
        val DATA_SIZE = 100_000
    }

    @Test
    fun dataSize() {
        val jsonStr = generateRandomData(DATA_SIZE).toJsonStr()
        val bytes = jsonStr.length
        val MB = bytes / 1E6
        println("data size: $MB MB")
    }

    @Test
    fun listPerformance() {
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
    fun sequencePerformance() {
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

}
