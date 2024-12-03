package aoc2024.day01

import kotlin.math.abs

fun main() {
    part1()
    part2()
}

fun part1() {
    println(calculateDistanceBetweenInputLists("/day01/input.txt"))
}

fun part2() {
    println(calculateSimilarityScoreOfInputLists("/day01/input.txt"))
}

fun calculateDistanceBetweenInputLists(uri: String) =
    readLists(uri)?.let { (firstList, secondList) -> calculateTotalDistanceBetween(firstList, secondList) }

fun calculateSimilarityScoreOfInputLists(uri: String) =
    readLists(uri)?.let { (firstList, secondList) -> calculateSimilarityScore(firstList, secondList) }

private fun readLists(uri: String): Pair<List<Int>, List<Int>>? = { }.javaClass.getResource(uri)
    ?.readText()
    ?.lines()
    ?.map { it.split("   ") }
    ?.filter { it.size == 2 }
    ?.map { Pair(it[0].toInt(), it[1].toInt()) }
    ?.unzip()

fun calculateTotalDistanceBetween(firstList: List<Int>, secondList: List<Int>) =
    firstList.sorted().zip(secondList.sorted()).sumOf { abs(it.second - it.first) }

fun calculateSimilarityScore(firstList: List<Int>, secondList: List<Int>) =
    firstList.sumOf { firstListEntry -> firstListEntry * secondList.count { firstListEntry == it } }