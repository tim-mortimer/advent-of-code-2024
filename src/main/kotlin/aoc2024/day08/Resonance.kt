package aoc2024.day08

import aoc2024.day03.readFileToList

private typealias Frequency = Char

fun main() {
    part1()
    part2()
}

fun part1() {
    val grid = readFileToList("/day08/input.txt").toGrid()
    println(grid.uniqueAntinodeLocations(Pair<Location, Location>::part1Antinodes).size)
}

fun part2() {
    val grid = readFileToList("/day08/input.txt").toGrid()
    println(grid.uniqueAntinodeLocations(Pair<Location, Location>::part2Antinodes).size)
}

fun List<String>.toGrid(): Grid {
    val cleaned = this.filter { it != "" }
    val width = cleaned[0].length
    val height = cleaned.size

    val frequencyToLocations = cleaned.flatMapIndexed { y, row ->
        row.mapIndexed { x, frequency ->
            frequency to Location(x, y)
        }
    }
        .filter { it.first != '.' }
        .groupBy(
            keySelector = { it.first },
            valueTransform = { it.second }
        )

    return Grid(width, height, frequencyToLocations)
}

data class Grid(
    val width: Int,
    val height: Int,
    val antennas: Map<Frequency, List<Location>>,
) {
    fun uniqueAntinodeLocations(antinodes: Pair<Location, Location>.(grid: Grid) -> List<Location>) =
        pairwiseAntennas()
            .mapValues { it.value.flatMap { antennaPair -> antennaPair.antinodes(this) } }
            .values
            .flatten()
            .toSet()

    fun contains(location: Location) = location.x in 0..<width && location.y in 0..<height

    private fun pairwiseAntennas(): Map<Frequency, List<Pair<Location, Location>>> {
        return antennas.mapValues { entry ->
            entry.value.flatMap { location ->
                entry.value.filter { it != location }.map { location to it }
            }
        }
    }
}

fun Pair<Location, Location>.part1Antinodes(grid: Grid) = listOf(
    this.first - gradient(),
    this.second + gradient()
).filter { grid.contains(it) }

fun Pair<Location, Location>.part2Antinodes(grid: Grid): List<Location> {
    val antinodes: MutableList<Location> = mutableListOf()

    var currentLocation = this.second
    while (grid.contains(currentLocation)) {
        antinodes += currentLocation
        currentLocation += gradient()
    }

    currentLocation = this.first
    while (grid.contains(currentLocation)) {
        antinodes += currentLocation
        currentLocation -= gradient()
    }

    return antinodes
}

operator fun Location.plus(gradient: Pair<Int, Int>) = Location(this.x + gradient.first, this.y + gradient.second)
operator fun Location.minus(gradient: Pair<Int, Int>) = Location(this.x - gradient.first, this.y - gradient.second)

private fun Pair<Location, Location>.gradient() = (second.x - first.x) to (second.y - first.y)

data class Location(val x: Int, val y: Int)
