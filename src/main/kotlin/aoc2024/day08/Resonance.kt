package aoc2024.day08

import aoc2024.day03.readFileToList

private typealias Frequency = Char

fun main() {
    part1()
}

fun part1() {
    val grid = readFileToList("/day08/input.txt").filter { it != "" }.toGrid()
    println(grid.uniqueAntinodeLocations(Pair<Location, Location>::part1Antinodes).size)
}

fun List<String>.toGrid(): Grid {
    val width = this[0].length
    val height = this.size

    val frequencyToLocations = this.flatMapIndexed { y, row ->
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
    fun uniqueAntinodeLocations(antinodes: Pair<Location, Location>.() -> List<Location>) =
        pairwiseAntennas()
            .mapValues { it.value.flatMap { it.antinodes() } }
            .values
            .flatten()
            .filter(this::contains)
            .toSet()

    private fun pairwiseAntennas(): Map<Frequency, List<Pair<Location, Location>>> {
        return antennas.mapValues { entry ->
            entry.value.flatMap { location ->
                entry.value.filter { it != location }.map { location to it }
            }
        }
    }

    private fun contains(location: Location) = location.x in 0..<width && location.y in 0..<height
}

fun Pair<Location, Location>.part1Antinodes() = listOf(
    Location(this.first.x - gradient().first, this.first.y - gradient().second),
    Location(this.second.x + gradient().first, this.second.y + gradient().second)
)

private fun Pair<Location, Location>.gradient() = (second.x - first.x) to (second.y - first.y)

data class Location(val x: Int, val y: Int)