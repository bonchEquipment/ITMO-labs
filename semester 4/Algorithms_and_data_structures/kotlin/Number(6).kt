/**
 * https://contest.yandex.ru/contest/35179/problems/F/
 */
package agronom

import kotlin.math.abs

fun main() {
    val count = 4
    val count2 = 1
    val test1: Array<String> = listOf("2", "20", "004", "66").toTypedArray()
    val test2: Array<String> = listOf("2").toTypedArray()

    quickSort(test1, 0, test1.size - 1)
    quickSort(test2, 0, test2.size - 1)
    for (i in 0 until count)
        print(test1[count - 1 - i]) //итерируемся из конца в начало

    println()

    for (i in 0 until count2)
        print(test2[count2 - 1 - i]) //итерируемся из конца в начало
}

fun quickSort(array: Array<String>, lowIndex: Int, highIndex: Int) {
    if (lowIndex >= highIndex) return

    val pivot = array[highIndex]
    var leftPointer = lowIndex
    var rightPointer = highIndex - 1

    while (leftPointer < rightPointer) {
        //while (rawArray[leftPointer] <= pivot)
        while (compare(pivot, array[leftPointer]) >= 0 && leftPointer < rightPointer)
            leftPointer++
        //while (rawArray[rightPointer] >= pivot)
        while (compare(pivot, array[rightPointer]) <= 0 && leftPointer < rightPointer)
            rightPointer--

        swap(array, leftPointer, rightPointer)
    }


    if (compare(array[leftPointer], array[highIndex])  > 0 )
        swap(array, leftPointer, highIndex)
    else
        leftPointer = highIndex

    quickSort(array, lowIndex, leftPointer - 1)
    quickSort(array, leftPointer + 1, highIndex)
}

private fun swap(rawArray: Array<String>, leftIndex: Int, rightIndex: Int) {
    val temp = rawArray[leftIndex]
    rawArray[leftIndex] = rawArray[rightIndex]
    rawArray[rightIndex] = temp
}

fun compare(a: String, b: String): Int {
    var mutableA = a
    var mutableB = b
    val firstLetterOfA = a.toCharArray()[0]
    val firstLetterOfB = b.toCharArray()[0]
    val lengthDifference = a.length - b.length

    for (i in 0 until abs(lengthDifference)) {
        if (lengthDifference > 0)
            mutableB += firstLetterOfB
        else if (lengthDifference < 0)
            mutableA += firstLetterOfA
    }

    return compareStringsWithEqualLength(mutableA, mutableB)
}

/**
 * returns true if a >= b
 */
private fun compareStringsWithEqualLength(a: String, b: String): Int {
    val aCharArray = a.toCharArray()
    val bCharArray = b.toCharArray()

    for (i in a.indices)
        if (aCharArray[i] < bCharArray[i]) return -1
        else if (aCharArray[i] > bCharArray[i]) return 1

    return 0
}