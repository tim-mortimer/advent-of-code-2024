package aoc2024.day1

import kotlin.math.abs

fun main() {
    part1()
}

fun part1() {
    println(calculateDistanceBetweenInputLists("/day1/input.txt"))
}

fun calculateDistanceBetweenInputLists(uri: String) =
    readLists(uri)?.let { (firstList, secondList) -> calculateTotalDistanceBetween(firstList, secondList) }

private fun readLists(uri: String): Pair<List<Int>, List<Int>>? {
    return { }.javaClass.getResource(uri)
        ?.readText()
        ?.lines()
        ?.map { it.split("   ") }
        ?.filter { it.size == 2 }
        ?.map { Pair(it[0].toInt(), it[1].toInt()) }
        ?.unzip()
}

fun calculateTotalDistanceBetween(firstList: List<Int>, secondList: List<Int>) =
    firstList.sorted().zip(secondList.sorted()).sumOf { abs(it.second - it.first) }
