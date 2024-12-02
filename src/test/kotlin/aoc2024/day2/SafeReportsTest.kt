package aoc2024.day2

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SafeReportsTest {

    @Test
    fun `reports with increasing levels of 1 to 3 are safe`() {
        assertTrue(Report(0, 3, 4, 6).isSafe())
    }

    @Test
    fun `reports that aren't strictly increasing are unsafe`() {
        assertFalse(Report(0, 1, 1, 2).isSafe())
    }

    @Test
    fun `reports that increase by more than 3 are unsafe`() {
        assertFalse(Report(0, 1, 5, 6).isSafe())
    }

    @Test
    fun `reports with decreasing levels of 1 to 3 are safe`() {
        assertTrue(Report(4, 1, 0, -2).isSafe())
    }

    @Test
    fun `reports that aren't strictly decreasing are unsafe`() {
        assertFalse(Report(4, 4, 2, 1).isSafe())
    }

    @Test
    fun `reports that decrease by more than 3 are unsafe`() {
        assertFalse(Report(4, 4, 0, -2).isSafe())
    }

    @Test
    fun `reports that increase and decrease are unsafe`() {
        assertFalse(Report(4, 1, 2, 0).isSafe())
    }
}
