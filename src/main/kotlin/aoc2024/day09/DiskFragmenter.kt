package aoc2024.day09

import aoc2024.day03.readFileToList
import java.util.*

fun main() {
    part1()
    part2()
}

fun part1() {
    println(compactFileBlockByBlock(expandDiskMap(readFileToList("/day09/input.txt")[0])).checksum())
}

fun part2() {
    println(compactFileFileByFile(expandDiskMap(readFileToList("/day09/input.txt")[0])).checksum())
}

fun expandDiskMap(diskMap: String): List<String> = diskMap.flatMapIndexed { index, blockSize ->
    when {
        index % 2 == 0 -> (1..blockSize.digitToInt()).map { (index / 2).toString() }
        else -> (1..blockSize.digitToInt()).map { "." }
    }
}

tailrec fun compactFileBlockByBlock(fileBlocks: List<String>, startingWith: List<String> = emptyList()): List<String> =
    when {
        fileBlocks.none { it == "." } -> startingWith + fileBlocks
        else -> {
            val firstFreeSpaceIndex = fileBlocks.indexOf(".")
            compactFileBlockByBlock(
                fileBlocks.drop(firstFreeSpaceIndex + 1).dropLast(1).dropLastWhile { it == "." },
                startingWith + fileBlocks.subList(0, firstFreeSpaceIndex) + fileBlocks.last()
            )
        }
    }

tailrec fun compactFileFileByFile(fileBlocks: List<String>, endingWith: List<String> = emptyList()): List<String> {
    if (fileBlocks.isEmpty()) return endingWith
    val freeBlocksAtEnd = fileBlocks.takeLastWhile { it == "." }
    val trimmed = fileBlocks.dropLast(freeBlocksAtEnd.size)
    val last = trimmed.last()
    val desiredBlockSize = trimmed.takeLastWhile { it == last }.size
    val desiredFreeSpace = (1..desiredBlockSize).map { "." }
    val indexOfFreeSpace = Collections.indexOfSubList(trimmed, desiredFreeSpace)

    return when {
        indexOfFreeSpace == -1 -> compactFileFileByFile(
            trimmed.dropLast(desiredBlockSize),
            trimmed.takeLast(desiredBlockSize) + freeBlocksAtEnd + endingWith
        )

        else -> compactFileFileByFile(
            trimmed.subList(0, indexOfFreeSpace) +
                    (1..desiredBlockSize).map { last } +
                    trimmed.drop(indexOfFreeSpace + desiredBlockSize).dropLast(desiredBlockSize) +
                    desiredFreeSpace,
            freeBlocksAtEnd + endingWith
        )
    }
}

fun List<String>.checksum() =
    this.mapIndexed { index, fileId -> if (fileId == ".") 0 else index * fileId.toLong() }.sum()
