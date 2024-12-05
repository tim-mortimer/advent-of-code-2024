package aoc2024.day05

import aoc2024.day04.readFileToList
import kotlin.test.Test
import kotlin.test.assertEquals

class PrintQueueTest {

    @Test
    fun `example input for print queue`() {
        val input = readFileToList("/day05/example.txt")

        assertEquals(143, sumMiddlePageNumbersOfCorrectUpdates(input.pageOrderingRules(), input.updates()))
    }
}
