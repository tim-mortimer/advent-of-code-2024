package aoc2024.day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MulInstructionsTest {

    @Test
    fun `empty list should yield 0`() {
        assertEquals(0, sumProducts(emptyList()))
        assertEquals(0, sumProducts(listOf("")))
    }

    @Test
    fun `a single mul instruction should yield the product`() {
        assertEquals(1, sumProducts(listOf("mul(1,1)")))
    }

    @Test
    fun `multiple mul instructions should are summed`() {
        assertEquals(5, sumProducts(listOf("mul(1,1)mul(2,2)")))
    }

    @Test
    fun `mul instructions can accept 1, 2 and 3 digit numbers`() {
        assertEquals(1, sumProducts(listOf("mul(1,1)")))
        assertEquals(120, sumProducts(listOf("mul(12,10)")))
        assertEquals(23400, sumProducts(listOf("mul(234,100)")))
        assertEquals(12, sumProducts(listOf("mul(12,1)")))
        assertEquals(120, sumProducts(listOf("mul(120,1)")))
        assertEquals(1200, sumProducts(listOf("mul(120,10)")))
    }

    @Test
    fun `invalid mul operations are ignored`() {
        assertEquals(4, sumProducts(listOf("mul(1, 1) !+dmul(2,2)+bcmul( 2, 3)")))
    }

    @Test
    fun `it works across multiple lines`() {
        assertEquals(5, sumProducts(listOf("mul(1,1)", "mul(2,2)")))
    }

    @Test
    fun `example input`() {
        assertEquals(161, sumProducts(readFileToList("/day03/example.txt")))
    }
}
