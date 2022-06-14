/**
 * https://contest.yandex.ru/contest/35179/problems/A/
 */
package agronom

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val length = scanner.nextInt()
    val flowers = IntArray(length)

    for (i in flowers.indices) {
        flowers[i] = scanner.nextInt()
    }

    val solution = count(flowers)

    printArray(solution)
}

fun printArray(array: Array<Int>){
    print("${array[0]}, ${array[1]}")
}

fun count(flowers: IntArray): Array<Int>{
    if (flowers.size < 3) return arrayOf(1, flowers.size)

    var startIndexOfMax = 0;
    var lengthOfMax = 0;
    var startIndexOfCur = 0;
    var lengthOfCur = 0

    for (i in 2 until flowers.size){
        if (flowers[i] == flowers[i - 1] && flowers [i - 2] == flowers[i]){
            startIndexOfCur = i - 1
            lengthOfCur = 2
            continue
        }
        lengthOfCur++

        if (lengthOfCur > lengthOfMax) {
            startIndexOfMax = startIndexOfCur
            lengthOfMax = lengthOfCur
        }
    }

    return arrayOf(startIndexOfMax + 1, startIndexOfMax + lengthOfMax);
}