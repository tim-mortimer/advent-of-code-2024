package aoc2024.day07

import aoc2024.day04.readFileToList

fun main() {
    part1()
    part2()
}

fun part1() {
    println(readFileToList("/day07/input.txt").toEquations().totalCalibrationResult(operationCount = 2))
}

fun part2() {
    println(readFileToList("/day07/input.txt").toEquations().totalCalibrationResult(operationCount = 3))
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

fun List<Equation>.totalCalibrationResult(operationCount: Int) =
    this.filter { it.possiblyTrue(operationCount) }.sumOf { it.testValue }

data class Equation(val testValue: Long, val operands: List<Int>) {
    fun possiblyTrue(operationCount: Int): Boolean {
        return operationCombinations(operationCount)
            .map { it.appliedToOperands() }
            .map { it == testValue }
            .any { it }
    }

    private fun operationCombinations(operationCount: Int): List<List<Operation>> {
        val numberOfPossibleCalculations = Math.pow(operationCount.toDouble(), (operands.size - 1).toDouble()).toInt()
        return (0..<numberOfPossibleCalculations).map { it.toOperationCombination(operationCount) }
    }

    private fun Int.toOperationCombination(operationCount: Int) = toString(operationCount)
        .padStart(length = operands.size - 1, padChar = '0')
        .asOperationList()

    private fun String.asOperationList() = map { digit ->
        when (digit) {
            '0' -> Add
            '1' -> Multiply
            '2' -> Concatenate
            else -> error("Invalid digit $digit")
        }
    }

    private fun List<Operation>.appliedToOperands() = foldIndexed(
        operands[0].toLong(),
        { index, acc, operation ->
            when (operation) {
                Add -> acc + operands[index + 1]
                Multiply -> acc * operands[index + 1]
                Concatenate -> (acc.toString() + operands[index + 1].toString()).toLong()
            }
        })

    private sealed interface Operation
    data object Add : Operation
    data object Multiply : Operation
    data object Concatenate : Operation
}
