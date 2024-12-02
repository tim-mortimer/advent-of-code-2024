package aoc2024.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RedNosedReportsTest {
    @Test
    fun `count safe reports`() {
        val reports = listOf(Report(0, 1, 2, 5), Report(0, 0), Report(0, 4), Report(0, -1), Report(0, -4))

        assertEquals(2, reports.safeQuantity())
    }

    @Test
    fun `example input`() {
        assertEquals(2, readReports("/day2/example.txt")?.safeQuantity())
    }
}
