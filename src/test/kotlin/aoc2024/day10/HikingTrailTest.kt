package aoc2024.day10

import aoc2024.day03.readFileToList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HikingTrailTest {
    @Test
    fun `example input`() {
        val input = readFileToList("/day10/example.txt").filter { it != "" }
        val trails = findTrails(input)
        assertEquals(9, trails.keys.size)
        assertEquals(36, trails.reachableNineHeightPositions())
        assertEquals(81, trails.distinctHikingTrails())
    }

    @Test
    fun `no trailheads`() {
        assertEquals(0, findTrails(listOf("1")).keys.size)
    }

    @Test
    fun `trailheads with no onward position`() {
        assertEquals(mapOf(pt(0, 0) to listOf(listOf(pos(pt(0, 0), 0)))), findTrails(listOf("0")))
        assertEquals(mapOf(pt(0, 0) to listOf(listOf(pos(pt(0, 0), 0)))), findTrails(listOf("02")))
        assertEquals(
            mapOf(
                pt(0, 0) to listOf(listOf(pos(pt(0, 0), 0))),
                pt(0, 1) to listOf(listOf(pos(pt(0, 1), 0)))
            ),
            findTrails(listOf("02", "03"))
        )
    }

    @Test
    fun `trailheads with one onward position`() {
        assertEquals(mapOf(pt(0, 0) to listOf(listOf(pos(pt(0, 0), 0), pos(pt(1, 0), 1)))), findTrails(listOf("01")))
    }

    @Test
    fun `trailheads with two onward positions`() {
        assertEquals(
            mapOf(
                pt(0, 0) to listOf(
                    listOf(pos(pt(0, 0), 0), pos(pt(1, 0), 1), pos(pt(2, 0), 2))
                )
            ),
            findTrails(listOf("012"))
        )
    }

    @Test
    fun `trails with crossing paths`() {
        assertEquals(
            listOf(
                listOf(pt(0, 0), pt(1, 0), pt(1, 1), pt(2, 1), pt(2, 2)),
                listOf(pt(0, 0), pt(1, 0), pt(1, 1), pt(1, 2), pt(2, 2)),
                listOf(pt(0, 0), pt(0, 1), pt(1, 1), pt(2, 1), pt(2, 2)),
                listOf(pt(0, 0), pt(0, 1), pt(1, 1), pt(1, 2), pt(2, 2)),
            ),
            findTrails(
                listOf(
                    "015",
                    "123",
                    "534"
                )
            )[pt(0, 0)]?.map { trail -> trail.map { it.point } }
        )
    }

    @Test
    fun `trails with crossing paths and distinct trailheads`() {
        assertEquals(
            listOf(
                listOf(pt(0, 0), pt(1, 0), pt(1, 1), pt(1, 2), pt(2, 2)),
                listOf(pt(2, 0), pt(1, 0), pt(1, 1), pt(1, 2), pt(2, 2)),
            ),
            findTrails(
                listOf(
                    "010",
                    "929",
                    "934"
                )
            ).values.flatten().map { trail -> trail.map { it.point } }
        )
    }

    @Test
    fun `a trail that splits`() {
        assertEquals(
            setOf(
                listOf(pos(pt(1, 0), 0), pos(pt(1, 1), 1), pos(pt(0, 1), 2), pos(pt(0, 2), 3)),
                listOf(pos(pt(1, 0), 0), pos(pt(1, 1), 1), pos(pt(2, 1), 2), pos(pt(2, 2), 3)),
            ),
            findTrails(
                listOf(
                    "909",
                    "212",
                    "393"
                )
            )[pt(1, 0)]?.toSet()
        )
    }

    private fun pt(x: Int, y: Int): Point = Point(x, y)
    private fun pos(point: Point, height: Int): Position = Position(point, height)
}
