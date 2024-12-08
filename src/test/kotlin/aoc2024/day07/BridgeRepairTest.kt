package aoc2024.day07

import aoc2024.day04.readFileToList
import kotlin.test.Test
import kotlin.test.assertEquals

class BridgeRepairTest {

    @Test
    fun `example input`() {
        val equations: List<Equation> = readFileToList("/day07/example.txt").toEquations()
        assertEquals(3749, equations.totalCalibrationResult(operationCount = 2))
    }

    @Test
    fun `example input part 2`() {
        val equations: List<Equation> = readFileToList("/day07/example.txt").toEquations()
        assertEquals(11387, equations.totalCalibrationResult(operationCount = 3))
    }
}
