package aoc2024.day2

import kotlin.math.abs

class Report(vararg val levels: Int) {
    private val differences: List<Int>
        get() = levels.drop(1)
            .mapIndexed { index, value -> value - levels[index] }

    fun isSafe() = differences
        .none { abs(it) > 3 } && (differences.all { it > 0 } || differences.all { it < 0 })
}