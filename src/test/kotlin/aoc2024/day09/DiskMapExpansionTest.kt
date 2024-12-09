package aoc2024.day09

import kotlin.test.Test
import kotlin.test.assertEquals

class DiskMapExpansionTest {
    @Test
    fun `a single one block file`() {
        assertEquals(listOf("0"), expandDiskMap("1"))
    }

    @Test
    fun `two single block files with no free space`() {
        assertEquals(listOf("0", "1"), expandDiskMap("101"))
    }

    @Test
    fun `tens of files`() {
        assertEquals(
            listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"),
            expandDiskMap("101010101010101010101")
        )
    }

    @Test
    fun `example expansions`() {
        assertEquals("0..111....22222".toList().map { it.toString() }, expandDiskMap("12345"))
        assertEquals(
            "00...111...2...333.44.5555.6666.777.888899".toList().map { it.toString() },
            expandDiskMap("2333133121414131402")
        )
    }
}
