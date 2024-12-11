package aoc2024.day11

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlutonianPebblesTest {
    @Test
    fun `example input blinking once`() {
        assertEquals("1 2024 1 0 9 9 2021976".toLongs(), blinkAt("0 1 10 99 999".toLongs(), 1))
    }

    @Test
    fun `extended input`() {
        assertEquals(
            "2097446912 14168 4048 2 0 2 4 40 48 2024 40 48 80 96 2 8 6 7 6 0 3 2".toLongs(),
            blinkAt("125 17".toLongs(), 6)
        )
    }

    @Test
    fun `pebbles engraved with a 0 change into a 1`() {
        assertEquals(listOf(1L), change(0L))
    }

    @Test
    fun `pebbles engraved with an event number of digits split into two`() {
        assertEquals(listOf(1L, 0L), change(10L))
        assertEquals(listOf(9L, 9L), change(99L))
        assertEquals(listOf(10L, 0L), change(1000L))
    }

    @Test
    fun `any other pebbles have their engraving multiplied by 2024`() {
        assertEquals(listOf(2024L), change(1L))
        assertEquals(listOf(2021976L), change(999L))
    }
}
