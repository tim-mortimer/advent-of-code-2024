package aoc2024.day04

import kotlin.math.min

typealias LetterGrid = List<String>

fun main() {
    part1()
    part2()
}

fun part1() {
    println(readFileToList("/day04/input.txt").xmasCount())
}

fun part2() {
    println(readFileToList("/day04/input.txt").xMasCount())
}

fun LetterGrid.xmasCount() = allStrings()
    .sumOf { "XMAS".toRegex().findAll(it).count() + "SAMX".toRegex().findAll(it).count() }

fun LetterGrid.xMasCount(): Int {
    var count = 0

    (1..<(width() - 1)).forEach { rowIndex ->
        (1..<(height() - 1)).forEach { columnIndex ->
            if (hasXMasCentredAt(rowIndex, columnIndex)) count += 1
        }
    }

    return count
}

private fun LetterGrid.allStrings() = verticalStrings() +
        horizontalStrings() +
        upwardsDiagonalStrings() +
        downwardsDiagonalStrings()

private fun LetterGrid.verticalStrings() =
    (0..<width())
        .toList()
        .map { columnIndex -> map { row -> row[columnIndex] }.joinToString("") }

private fun LetterGrid.horizontalStrings() = this

private fun LetterGrid.upwardsDiagonalStrings() =
    upwardsDiagonalStringsFromLeft() + upwardsDiagonalStringsFromBottom()

private fun LetterGrid.upwardsDiagonalStringsFromBottom() = (1..<width()).toList()
    .map { columnIndex ->
        val diagonalLength = min(height(), width() - columnIndex)
        val rowsInDiagonal = ((height() - 1) downTo (height() - diagonalLength)).toList()
        rowsInDiagonal.map { rowIndex ->
            this[rowIndex][columnIndex + height() - rowIndex - 1]
        }.joinToString("")
    }

private fun LetterGrid.upwardsDiagonalStringsFromLeft() = (0..<height()).toList()
    .map { rowIndex ->
        val diagonalLength = min(width(), rowIndex + 1)
        val columnsInDiagonal = (0..<diagonalLength).toList()
        columnsInDiagonal.map { columnIndex ->
            this[rowIndex - columnIndex][columnIndex]
        }.joinToString("")
    }

private fun LetterGrid.downwardsDiagonalStrings() =
    downwardsDiagonalStringsFromLeft() + downwardsDiagonalStringsFromTop()

private fun LetterGrid.downwardsDiagonalStringsFromTop() = (1..<width()).toList()
    .map { columnIndex ->
        val diagonalLength = min(height(), width() - columnIndex)
        val rowsInDiagonal = (0..<diagonalLength).toList()
        rowsInDiagonal.map { rowIndex ->
            this[rowIndex][columnIndex + rowIndex]
        }.joinToString("")
    }

private fun LetterGrid.downwardsDiagonalStringsFromLeft() = (0..<height()).toList()
    .map { rowIndex ->
        val diagonalLength = min(width(), height() - rowIndex)
        val columnsInDiagonal = (0..<diagonalLength).toList()
        columnsInDiagonal.map { columnIndex ->
            this[rowIndex + columnIndex][columnIndex]
        }
            .joinToString("")
    }

private fun LetterGrid.height() = size
private fun LetterGrid.width() = this[0].length

private fun LetterGrid.hasXMasCentredAt(rowIndex: Int, columnIndex: Int): Boolean {
    val downwardsDiagonal = "${this[rowIndex - 1][columnIndex - 1]}${this[rowIndex][columnIndex]}${this[rowIndex + 1][columnIndex + 1]}"
    val upwardsDiagonal = "${this[rowIndex + 1][columnIndex - 1]}${this[rowIndex][columnIndex]}${this[rowIndex - 1][columnIndex + 1]}"
    val searchTerms = listOf("MAS", "SAM")
    return searchTerms.contains(downwardsDiagonal) && searchTerms.contains(upwardsDiagonal)
}

fun readFileToList(input: String) = ({}.javaClass.getResource(input)
    ?.readText()
    ?.lines()
    ?.filter { it.isNotBlank() }
    ?: emptyList())
