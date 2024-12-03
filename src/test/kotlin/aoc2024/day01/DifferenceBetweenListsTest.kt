package aoc2024.day01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DifferenceBetweenListsTest {
    @Test
    fun `two empty lists should return 0`() {
        assertEquals(0, calculateTotalDistanceBetween(emptyList(), emptyList()))
    }

    @Test
    fun `two lists containing the same number should return 0`() {
        assertEquals(0, calculateTotalDistanceBetween(listOf(2), listOf(2)))
    }

    @Test
    fun `two lists containing the one entry of difference +1 should return 1`() {
        assertEquals(1, calculateTotalDistanceBetween(listOf(1), listOf(2)))
    }

    @Test
    fun `two lists containing the one entry of difference 2 should return 2`() {
        assertEquals(2, calculateTotalDistanceBetween(listOf(1), listOf(3)))
    }

    @Test
    fun `two lists containing the one entry of difference -2 should return 2`() {
        assertEquals(2, calculateTotalDistanceBetween(listOf(3), listOf(1)))
    }

    @Test
    fun `two lists with three elements each`() {
        assertEquals(4, calculateTotalDistanceBetween(listOf(3, 5, 1), listOf(1, 2, 8)))
    }

    @Test
    fun `example input`() {
        val exampleInputURI = "/day01/example.txt"

        assertEquals(11, calculateDistanceBetweenInputLists(exampleInputURI))
    }
}

