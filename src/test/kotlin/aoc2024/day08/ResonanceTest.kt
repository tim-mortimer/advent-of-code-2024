package aoc2024.day08

import aoc2024.day03.readFileToList
import kotlin.test.Test
import kotlin.test.assertEquals

class ResonanceTest {
    @Test
    fun `part 1 example`() {
        val input = readFileToList("/day08/example.txt")
        assertEquals(14, input.toGrid().uniqueAntinodeLocations(Pair<Location, Location>::part1Antinodes).size)
    }
}
