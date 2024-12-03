package aoc2024.day02

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RedNosedReportsTest {
    @Test
    fun `count safe reports`() {
        val reports = listOf(Report(0, 1, 2, 5), Report(0, 0), Report(0, 4), Report(0, -1), Report(0, -4))

        assertEquals(2, reports.safeQuantity())
    }

    @Test
    fun `example input for counting safe reports`() {
        assertEquals(2, readReports("/day02/example.txt")?.safeQuantity())
    }

    @Test
    fun `example input for counting tolerably safe reports`() {
        assertEquals(4, readReports("/day02/example.txt")?.tolerablySafeQuantity())
    }
}
