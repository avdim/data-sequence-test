package org.sample.data

import kotlin.random.Random

val firstNames = listOf(
    "Серёжа",
    "Саша",
    "Петя",
    "Ваня",
    "Ира",
    "Света",
    "Женя",
    "Лёша",
    "Андрей",
    "Стас",
    "Кирилл",
    "Дима",
)

val secondNames = listOf(
    "Иванов",
    "Петров",
    "Смирнов",
    "Смирнов",
    "Розов",
    "Черноусов",
    "Эйнштейн",
    "Колмагоров",
)

val RUSSIA = "Russia"
val USA = "USA"

val countries = listOf(
    RUSSIA,
    USA,
    "Spain",
    "Italy",
    "France"
)

fun generateRandomData(size: Int): List<Person> =
    List(size) {
        Person(
            firstName = firstNames.random(),
            secondName = secondNames.random(),
            age = Random.nextInt(18, 100),
            country = countries.random(),
            weight = Random.nextInt(400, 1000) / 10f
        )
    }

