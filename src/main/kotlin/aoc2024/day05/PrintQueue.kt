package aoc2024.day05

import aoc2024.day04.readFileToList

val pageOrderRegex = """\d+\|\d+""".toRegex()

fun main() {
    part1()
}

fun part1() {
    val input = readFileToList("/day05/input.txt")

    println(sumMiddlePageNumbersOfCorrectUpdates(input.pageOrderingRules(), input.updates()))
}

fun List<String>.pageOrderingRules() = filter { it.matches(pageOrderRegex) }
    .map { it.split("|") }
    .map { mapOf(it[0].toInt() to listOf(it[1].toInt())) }
    .fold(mutableMapOf<Int, MutableList<Int>>()) { acc, map ->
        map.forEach { (key, value) ->
            acc.merge(key, value.toMutableList()) { oldList, newList ->
                oldList.apply { addAll(newList) }
            }
        }
        acc
    }.mapValues { it.value.toList() }

fun List<String>.updates(): List<List<Int>> {
    return filterNot { it.matches(pageOrderRegex) }
        .map { it.split(",") }
        .map { it.map { str -> str.toInt() } }
}

fun sumMiddlePageNumbersOfCorrectUpdates(
    pageOrderingRules: Map<Int, List<Int>>,
    updates: List<List<Int>>
) = updates.filter { it.compliesWith(pageOrderingRules) }.sumOf { it.middlePageNumber() }

private fun List<Int>.compliesWith(pageOrderingRules: Map<Int, List<Int>>) =
    this.mapIndexed { index, pageNumber ->
        pageOrderingRules[pageNumber].orEmpty().intersect(this.subList(0, index).toSet()).isEmpty()
    }.all { it }

private fun List<Int>.middlePageNumber() = this[(this.size / 2)]
