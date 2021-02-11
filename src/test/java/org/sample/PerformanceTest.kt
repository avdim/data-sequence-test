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
        val jsonStr: String = generateRandomData(DATA_SIZE).toJsonStr()
        val bytes = jsonStr.length
        val MB = bytes / 1E6
        println("data size: $MB MB")
    }

    @Test
    fun listPerformance() {
        val list = generateRandomData(DATA_SIZE)

        val result = calcAverageNanoDelay {
            list
                .filter{true}
                .filter{true}
                .filter{true}
                .filter { it.country == RUSSIA }
                .filter { it.firstName.startsWith("Д") }
                .sortedBy { it.weight }
                .take(1000)
        }
//   N * log(N)
        result.data.forEach {
            println(it)
        }
    }

    @Test
    fun sequencePerformance() {
        val sequence = generateRandomData(DATA_SIZE).asSequence()

        val result = calcAverageNanoDelay {
            sequence
                .filter { true } // N
                .filter { true } // ( N  *  )
                .filter { true } //
                .filter { true }
                .filter { it.age > 40 }
                .filter { it.country == RUSSIA }
                .filter { it.firstName.startsWith("Д") }
                .sortedBy { it.weight }// order  // N log N
                .take(1000)
                .toList()
        }

        result.data.forEach {
            println(it)
        }
    }

}

val mapOfOffers = mapOf<Int, Offer>() // N insert ( log N)

data class Offer(val id:String, val tripId:Int)

data class Trip(val id :String, val carrier:Int)
