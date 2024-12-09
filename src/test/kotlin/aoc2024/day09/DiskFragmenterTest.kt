package aoc2024.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskFragmenterTest {
    @Test
    fun `example input`() {
        val result = defrag("2333133121414131402")
        assertEquals("0099811188827773336446555566".toList().map { it.toString() }, result)
        assertEquals(1928, result.checksum().toInt())
    }
}
