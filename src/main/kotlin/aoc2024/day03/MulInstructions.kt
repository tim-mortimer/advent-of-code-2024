package aoc2024.day03

fun main() {
    part1()
}

fun part1() {
    println(sumProducts(readFileToList("/day03/input.txt")))
}

fun sumProducts(input: List<String>): Long {
    val regex = """mul\(([0-9]{1,3}),([0-9]{1,3})\)""".toRegex()
    return input.map { line -> regex.findAll(line).toList() }
        .flatten()
        .sumOf {it.destructured.let { (a, b) -> a.toLong() * b.toLong() } }
}

fun readFileToList(input: String) = ({}.javaClass.getResource(input)
    ?.readText()
    ?.lines()
    ?: emptyList())
