package aoc2024.day2


fun main() {
    part1()
}

fun part1() {
    println(readReports("/day2/input.txt")?.safeQuantity())
}

fun List<Report>.safeQuantity() = this.count { it.isSafe() }

fun readReports(input: String) = {}.javaClass.getResource(input)
    ?.readText()
    ?.lines()
    ?.filter { it != "" }
    ?.map { it.split(" ") }
    ?.map { reportOfStrings -> reportOfStrings.map { it.toInt() } }
    ?.map { Report(*it.toIntArray()) }