/**
 * https://contest.yandex.ru/contest/35179/problems/H/
 */

package agronom

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val productCount = 5
    val saleRate = 2
    val test1: Array<Int> = listOf(200, 100, 1000, 400, 100).toTypedArray()

    quickSort(test1, 0, test1.size - 1)

    var amountToPay: Long = 0
    var saleCount = 0

    for (i in test1.indices){
        saleCount++
        if (saleCount % saleRate == 0) continue
        amountToPay += test1[i]
    }

    println(amountToPay)

}

private fun swap(rawArray: Array<Int>, leftIndex: Int, rightIndex: Int) {
    val temp = rawArray[leftIndex]
    rawArray[leftIndex] = rawArray[rightIndex]
    rawArray[rightIndex] = temp
}


fun quickSort(array: Array<Int>, lowIndex: Int, highIndex: Int) {
    if (lowIndex >= highIndex) return

    val pivot = array[highIndex]
    var leftPointer = lowIndex
    var rightPointer = highIndex - 1

    while (leftPointer < rightPointer) {
        //while (rawArray[leftPointer] <= pivot)
        while (array[leftPointer] <= pivot && leftPointer < rightPointer)
            leftPointer++
        //while (rawArray[rightPointer] >= pivot)
        while (array[leftPointer] >= pivot && leftPointer < rightPointer)
            rightPointer--

        swap(array, leftPointer, rightPointer)
    }


    if (array[leftPointer] > array[highIndex])
        swap(array, leftPointer, highIndex)
    else
        leftPointer = highIndex

    quickSort(array, lowIndex, leftPointer - 1)
    quickSort(array, leftPointer + 1, highIndex)
}