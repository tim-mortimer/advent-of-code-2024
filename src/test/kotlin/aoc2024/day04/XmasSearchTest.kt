package aoc2024.day04

import kotlin.test.Test
import kotlin.test.assertEquals

class XmasSearchTest {

    @Test
    fun `example input for xmas search`() {
        val input = readFileToList("/day04/example.txt")

        assertEquals(18, input.xmasCount())
    }

    @Test
    fun `example input for x-mas search`() {
        val input = readFileToList("/day04/example.txt")

        assertEquals(9, input.xMasCount())
    }
}
