package org.sample.data

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Person(
    val firstName: String,
    val secondName: String,
    val age: Int,
    val country: String,
    val weight:Float
) {
    override fun toString(): String = "$firstName $secondName, $age y.o. from $country, w: $weight"
}

fun List<Person>.toJsonStr():String =
    Json.encodeToString(ListSerializer(Person.serializer()), this)
