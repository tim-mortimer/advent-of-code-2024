package aoc2024.day09

import aoc2024.day03.readFileToList

fun main() {
    part1()
}

fun part1() {
    println(defrag(readFileToList("/day09/input.txt")[0]).checksum())
}

fun defrag(diskMap: String) = compactFile(expandDiskMap(diskMap))

fun expandDiskMap(diskMap: String): List<String> = diskMap.flatMapIndexed { index, blockSize ->
    when {
        index % 2 == 0 -> (1..blockSize.digitToInt()).map { (index / 2).toString() }
        else -> (1..blockSize.digitToInt()).map { "." }
    }
}

tailrec fun compactFile(fileBlocks: List<String>, startingWith: List<String> = emptyList()): List<String> = when {
    fileBlocks.none { it == "." } -> startingWith + fileBlocks
    else -> {
        val firstFreeSpaceIndex = fileBlocks.indexOf(".")
        compactFile(
            fileBlocks.drop(firstFreeSpaceIndex + 1).dropLast(1).dropLastWhile { it == "." },
            startingWith + fileBlocks.subList(0, firstFreeSpaceIndex) + fileBlocks.last()
        )
    }
}

fun List<String>.checksum() = this.mapIndexed { index, fileId -> index * fileId.toLong() }.sum()
