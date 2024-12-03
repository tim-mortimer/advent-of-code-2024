package aoc2024.day03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MulInstructionsTest {

    @Test
    fun `empty list should yield 0`() {
        assertEquals(0, emptyList<String>().evaluateMulOperations())
        assertEquals(0, listOf("").evaluateMulOperations())
    }

    @Test
    fun `a single mul instruction should yield the product`() {
        assertEquals(1, listOf("mul(1,1)").evaluateMulOperations())
    }

    @Test
    fun `multiple mul instructions should are summed`() {
        assertEquals(5, listOf("mul(1,1)mul(2,2)").evaluateMulOperations())
    }

    @Test
    fun `mul instructions can accept 1, 2 and 3 digit numbers`() {
        assertEquals(1, listOf("mul(1,1)").evaluateMulOperations())
        assertEquals(120, listOf("mul(12,10)").evaluateMulOperations())
        assertEquals(23400, listOf("mul(234,100)").evaluateMulOperations())
        assertEquals(12, listOf("mul(12,1)").evaluateMulOperations())
        assertEquals(120, listOf("mul(120,1)").evaluateMulOperations())
        assertEquals(1200, listOf("mul(120,10)").evaluateMulOperations())
    }

    @Test
    fun `invalid mul operations are ignored`() {
        assertEquals(4, listOf("mul(1, 1) !+dmul(2,2)+bcmul( 2, 3)").evaluateMulOperations())
    }

    @Test
    fun `it works across multiple lines`() {
        assertEquals(5, listOf("mul(1,1)", "mul(2,2)").evaluateMulOperations())
    }

    @Test
    fun `example input`() {
        assertEquals(161, readFileToList("/day03/example_part01.txt").evaluateMulOperations())
    }

    @Test
    fun `evaluate a simple string with conditionals`() {
        assertEquals(0, "don't()do()".evaluateMulOperationsWithConditionals())
        assertEquals(0, "don't()mul(1,2)do()".evaluateMulOperationsWithConditionals())
        assertEquals(0, "don't()mul(1,2)do()".evaluateMulOperationsWithConditionals())
        assertEquals(2, "mul(1,2)don't()mul(3,4)".evaluateMulOperationsWithConditionals())
        assertEquals(10, "mul(1,2)don't()mul(3,4)do()mul(2,4)".evaluateMulOperationsWithConditionals())
        assertEquals(11, "mul(1,2)don't()mul(3,4)do()mul(1,1)don't()do()mul(2,4)".evaluateMulOperationsWithConditionals())
        assertEquals(3, "mul(1,2)don't()mul(3,4)do()mul(1,1)don't()mul(2,4)".evaluateMulOperationsWithConditionals())
    }

    @Test
    fun `example input with conditionals`() {
        assertEquals(48, readFileToList("/day03/example_part02.txt").evaluateMulOperationsWithConditionals())
    }
}
