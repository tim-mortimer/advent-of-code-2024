package aoc2024.day10

import aoc2024.day03.readFileToList
import kotlin.math.abs

fun main() {
    part1()
}

fun part1() {
    val input = readFileToList("/day10/input.txt").filter { it != "" }
    println(findTrails(input).totalScore())
}

fun findTrails(input: List<String>) = input.positions()
    .filter { it.height == 0 }
    .groupBy { it }
    .mapValues { entry -> trailsFrom(entry.key, input.positions()) }
    .mapKeys { entry -> entry.key.point }

private fun List<String>.positions() = flatMapIndexed { y, row ->
    row.mapIndexed { x, height -> Position(Point(x, y), height.digitToInt()) }
}

private fun trailsFrom(position: Position, positions: List<Position>): List<Trail> {
    val surroundingPositions = positions.filter { it.distanceFrom(position) == 1 && it.height == position.height + 1 }

    return when {
        surroundingPositions.isEmpty() -> listOf(listOf(position))
        else -> surroundingPositions.flatMap { trailsFrom(it, positions) }.map { listOf(position) + it }
    }
}

fun Map<Point, List<Trail>>.totalScore() = this.values.sumOf(List<Trail>::score)

private fun List<Trail>.score() =
    filter { trail -> trail.any { position -> position.height == 9 } }
        .groupBy { trail -> trail.find { position -> position.height == 9 } }
        .keys
        .size

typealias Trail = List<Position>

data class Position(val point: Point, val height: Int) {
    fun distanceFrom(position: Position): Int {
        return this.point.distanceFrom(position.point)
    }
}

data class Point(val x: Int, val y: Int) {
    fun distanceFrom(point: Point): Int = abs(this.x - point.x) + abs(this.y - point.y)
}
