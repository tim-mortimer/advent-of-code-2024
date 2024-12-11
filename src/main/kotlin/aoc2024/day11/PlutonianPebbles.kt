package aoc2024.day11

fun main() {
    part1()
}

fun part1() {
    println(blinkAt("9759 0 256219 60 1175776 113 6 92833".toLongs(), 25).size)
}

fun String.toLongs() = this.split(" ").map { it.toLong() }

tailrec fun blinkAt(input: List<Long>, times: Int): List<Long> = when {
    times == 0 -> input
    else -> blinkAt(input.flatMap { change(it) }, times - 1)
}

fun change(engraving: Long): List<Long> {
    val length = engraving.toString().length
    return when {
        engraving == 0L -> listOf(1)
        length % 2 == 0 -> listOf(
            engraving.toString().take(length / 2).toLong(),
            engraving.toString().takeLast(length / 2).toLong()
        )
        else -> listOf(engraving * 2024)
    }
}
