package aoc2024.day09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DiskFragmenterTest {
    @Test
    fun `part 1 example input`() {
        val result = compactFileBlockByBlock(expandDiskMap("2333133121414131402"))
        assertEquals(1928, result.checksum())
    }

    @Test
    fun `part 2 example input`() {
        val result = compactFileFileByFile(expandDiskMap("2333133121414131402"))
        assertEquals(2858, result.checksum())
    }
}
