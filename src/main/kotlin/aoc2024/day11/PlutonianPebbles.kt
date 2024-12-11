package aoc2024.day11

fun main() {
    part1()
    part2()
}

fun part1() {
    println(blinkAt("9759 0 256219 60 1175776 113 6 92833".toEngravings(), 25))
}

fun part2() {
    println(blinkAt("9759 0 256219 60 1175776 113 6 92833".toEngravings(), 75))
}

typealias Engraving = Long

fun String.toEngravings() = this.split(" ").map { it.toLong() }

fun blinkAt(input: List<Engraving>, times: Int): Long {
    return input.fold(0) { count, item -> count + blinkAt(item, times) }
}

fun blinkAt(engraving: Engraving, times: Int = 1): Long {
    if (engravingCache[(engraving to times)] != null) return engravingCache[engraving to times]!!

    return when {
        times == 0 -> 1
        else -> blinkAt(engraving.change(), times - 1)
    }.also { engravingCache[engraving to times] = it }
}

val engravingCache = mutableMapOf<Pair<Engraving, Int>, Long>()

fun Engraving.change() = when {
    this == 0L -> listOf(1L)
    toString().length % 2 == 0 -> listOf(
        toString().take(toString().length / 2).toLong(),
        toString().takeLast(toString().length / 2).toLong()
    )
    else -> listOf(this * 2024)
}
