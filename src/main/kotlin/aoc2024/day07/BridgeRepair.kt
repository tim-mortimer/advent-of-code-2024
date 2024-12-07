package aoc2024.day07

import aoc2024.day04.readFileToList

fun main() {
    part1()
}

fun part1() {
    println(readFileToList("/day07/input.txt").toEquations().totalCalibrationResult())
}

fun List<String>.toEquations() = this.map { it.toEquation() }

private fun String.toEquation(): Equation {
    val matchResult: MatchResult =
        """^(\d+):((?:\s\d+)+)$""".toRegex().matchEntire(this) ?: error("Invalid input string: $this")

    val testValue = matchResult.groupValues[1].toLong()
    val operands = matchResult.groupValues[2].trim().split(" ").map { it.toInt() }

    return Equation(
        testValue,
        operands
    )
}

fun List<Equation>.totalCalibrationResult() = this.filter { it.possiblyTrue() }.sumOf { it.testValue }

data class Equation(val testValue: Long, val operands: List<Int>) {
    fun possiblyTrue(): Boolean {
        return operationCombinations()
            .map { it.appliedToOperands() }
            .map { it == testValue }
            .any { it }
    }

    private fun operationCombinations(): List<List<Operation>> {
        val numberOfPossibleCalculations = Math.pow(2.toDouble(), (operands.size - 1).toDouble()).toInt()
        return (0..<numberOfPossibleCalculations).map { it.toOperationCombination() }
    }

    private fun Int.toOperationCombination() = binaryRepresentation(length = operands.size - 1)
        .asOperationList()

    private fun Int.binaryRepresentation(length: Int) = toString(2).padStart(length, '0')

    private fun String.asOperationList() = map { digit ->
        when (digit) {
            '0' -> Add
            '1' -> Multiply
            else -> error("Invalid binary digit $digit")
        }
    }

    private fun List<Operation>.appliedToOperands() = foldIndexed(
        operands[0].toLong(),
        { index, acc, operation ->
            when (operation) {
                Add -> acc + operands[index + 1]
                Multiply -> acc * operands[index + 1]
            }
        })

    private sealed interface Operation
    data object Add : Operation
    data object Multiply : Operation
}

