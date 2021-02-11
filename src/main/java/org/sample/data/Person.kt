package org.sample.data

data class Person(
    val firstName: String,
    val secondName: String,
    val age: Int,
    val country: String,
    val weight:Float
) {
    override fun toString(): String = "$firstName $secondName, $age y.o. from $country, w: $weight"
}
