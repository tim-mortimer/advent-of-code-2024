package aoc2024.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FileCompactionTest {
    @Test
    fun `a single file block`() {
        assertEquals(listOf("0"), compactFile(listOf("0")))
    }

    @Test
    fun `two single file blocks with no free space`() {
        assertEquals(listOf("0", "1"), compactFile(listOf("0", "1")))
    }

    @Test
    fun `two single file blocks with one block of free space between them`() {
        assertEquals(listOf("0", "1"), compactFile(listOf("0", ".", "1")))
    }

    @Test
    fun `three single file blocks with one block of free space between them`() {
        assertEquals(listOf("0", "2", "1"), compactFile(listOf("0", ".", "1", ".", "2")))
    }

    @Test
    fun `example compactions`() {
        assertEquals(
            "022111222".toList().map { it.toString() },
            compactFile("0..111....22222".toList().map { it.toString() })
        )
        assertEquals(
            "0099811188827773336446555566".toList().map { it.toString() },
            compactFile("00...111...2...333.44.5555.6666.777.888899".toList().map { it.toString() })
        )
    }
}
