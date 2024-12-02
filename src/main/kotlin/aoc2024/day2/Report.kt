package aoc2024.day2

import kotlin.math.abs

class Report(private vararg val levels: Int) {
    private val differences: List<Int>
        get() = levels.drop(1)
            .mapIndexed { index, value -> value - levels[index] }

    fun isSafe() = differences
        .none { abs(it) > 3 } && (differences.all { it > 0 } || differences.all { it < 0 })

    fun isTolerablySafe() =
        levels.mapIndexed { index, _ -> Report(*levels.withoutElementAt(index)).isSafe() }.contains(true)

    private fun IntArray.withoutElementAt(index: Int): IntArray {
        val mutableList = this.toMutableList()
        mutableList.removeAt(index)
        return mutableList.toIntArray()
    }
}