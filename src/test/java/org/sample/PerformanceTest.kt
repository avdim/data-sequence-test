package org.sample

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.sample.data.Person
import org.sample.data.generateRandomData
import kotlin.test.assertEquals

class PerformanceTest() {
  @Test
  fun perf1() {
    val data: List<Person> = generateRandomData(100_000)
    calcAverageNanoDelay("sort", 10) {
      data.sortedBy { it.weight }
    }
  }

  @Test
  fun testCoroutine() = runBlocking {
    delay(1)
    println("test coroutines")
    Unit
  }

}
