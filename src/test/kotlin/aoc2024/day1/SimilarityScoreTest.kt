package aoc2024.day1

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimilarityScoreTest {
    @Test
    fun `two empty lists give 0`() {
        assertEquals(0, calculateSimilarityScore(emptyList<Int>(), emptyList<Int>()))
    }

    @Test
    fun `two identical lists give the value in the first list`() {
        assertEquals(2, calculateSimilarityScore(listOf(2), listOf(2)))
    }

    @Test
    fun `two lists with one repeat in second list, one single and one missing`() {
        assertEquals(5, calculateSimilarityScore(listOf(2, 1, 5), listOf(2, 2, 1)))
    }

    @Test
    fun `example input`() {
        assertEquals(31, calculateSimilarityScoreOfInputLists("/day1/example.txt"))
    }
}
