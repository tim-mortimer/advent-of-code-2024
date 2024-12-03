package aoc2024.day03

fun main() {
    part1()
    part2()
}

fun part1() {
    println(readFileToList("/day03/input.txt").evaluateMulOperations())
}

fun part2() {
    println(readFileToList("/day03/input.txt").evaluateMulOperationsWithConditionals())
}

fun List<String>.evaluateMulOperations() = this.joinToString("").evaluateMulOperations()

fun String.evaluateMulOperations() = """mul\(([0-9]{1,3}),([0-9]{1,3})\)"""
    .toRegex()
    .findAll(this)
    .sumOf {
        it.destructured.let { (a, b) -> a.toLong() * b.toLong() }
    }

fun List<String>.evaluateMulOperationsWithConditionals() =
    this.joinToString("").evaluateMulOperationsWithConditionals()

fun String.evaluateMulOperationsWithConditionals() =
    this.replace("""don't\(\).*?do\(\)""".toRegex(), "")
        .replace("""don't\(\).*""".toRegex(), "")
        .evaluateMulOperations()

fun readFileToList(input: String) = ({}.javaClass.getResource(input)
    ?.readText()
    ?.lines()
    ?: emptyList())
