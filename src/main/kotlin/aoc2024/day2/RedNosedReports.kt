package aoc2024.day2

fun main() {
    part1()
    part2()
}

fun part1() {
    println(readReports("/day2/input.txt")?.safeQuantity())
}

fun part2() {
    println(readReports("/day2/input.txt")?.tolerablySafeQuantity())
}

fun List<Report>.safeQuantity() = this.count { it.isSafe() }

fun List<Report>.tolerablySafeQuantity() = this.count { it.isTolerablySafe() }

fun readReports(input: String) = {}.javaClass.getResource(input)
    ?.readText()
    ?.lines()
    ?.filter { it != "" }
    ?.map { it.split(" ") }
    ?.map { reportOfStrings -> reportOfStrings.map { it.toInt() } }
    ?.map { Report(*it.toIntArray()) }