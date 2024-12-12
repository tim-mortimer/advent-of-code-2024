package aoc2024.day12

import aoc2024.day04.readFileToList

fun main() {
    part1()
}

fun part1() {
    println(calculatePrice(readFileToList("/day12/input.txt")))
}

fun calculatePrice(input: List<String>): Long {
    val plots = input.mapIndexed { y, row -> row.mapIndexed { x, plant -> Plot(Position(x, y), plant) } }
        .flatten()

    val plotsToRegions = mapPlotsToRegions(plots)

    return plots.sumOf {
        val regionArea = plotsToRegions.keys.filter { key -> plotsToRegions[key] == plotsToRegions[it] }.size.toLong()
        it.perimeterContribution(plots) * regionArea
    }
}

private fun mapPlotsToRegions(plots: List<Plot>): Map<Plot, Plot> {
    return plots.fold(mutableMapOf()) { map, plot ->
        if (!map.containsKey(plot)) map[plot] = plot
        plot.map(plots, map)
        map
    }
}

private fun Plot.map(
    plots: List<Plot>,
    map: MutableMap<Plot, Plot>
) {
    val surroundingPlotsInRegion = surroundingPlotsInRegion(plots)

    (surroundingPlotsInRegion - surroundingPlotsInRegion.intersect(map.keys)).forEach { surroundingPlot ->
        if (!map.containsKey(surroundingPlot)) map[surroundingPlot] = map[this]!!
        surroundingPlot.map(plots, map)
    }
}

data class Plot(val position: Position, val plant: Plant) {
    fun perimeterContribution(plots: List<Plot>) = 4 - surroundingPlotsInRegion(plots).size

    fun surroundingPlotsInRegion(plots: List<Plot>) = plots.filter { it.plant == this.plant }
        .filter { surroundingPositions(plots).contains(it.position) }

    private fun surroundingPositions(plots: List<Plot>) = listOf(
        Position(position.x - 1, position.y),
        Position(position.x, position.y - 1),
        Position(position.x + 1, position.y),
        Position(position.x, position.y + 1),
    )
        .filter { it.x >= 0 && it.y >= 0 && it.x <= plots.greatestX() && it.y <= plots.greatestY() }
}

fun List<Plot>.greatestX() =
    if (greatestX != null) greatestX!! else this.map { it.position.x }.maxOf { it }.also { greatestX = it }

fun List<Plot>.greatestY() =
    if (greatestY != null) greatestY!! else this.map { it.position.y }.maxOf { it }.also { greatestY = it }

var greatestX: Int? = null
var greatestY: Int? = null

data class Position(val x: Int, val y: Int)
typealias Plant = Char
