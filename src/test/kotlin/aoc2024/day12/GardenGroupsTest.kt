package aoc2024.day12

import aoc2024.day04.readFileToList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GardenGroupsTest {

    @Test
    fun `example input`() {
        val input = readFileToList("/day12/example.txt")
        assertEquals(1930, calculatePrice(input))
    }

    @Test
    fun `smaller example input`() {
        val actual = calculatePrice(
            listOf(
                "AAAA",
                "BBCD",
                "BBCC",
                "EEEC"
            )
        )
        assertEquals(140, actual)
    }
}
