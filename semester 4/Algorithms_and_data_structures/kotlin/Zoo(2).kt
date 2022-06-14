/**
 * https://contest.yandex.ru/contest/35179/problems/B/
 */

import java.util.*

fun main() {

    val tests = listOf("ABba", "ABab", "AAbaaB", "acaACAbB", "cAbBabBaAC")

    val results = listOf(
        "need to be: Possible 2 1 ",
        "need to be: Impossible",
        "need to be: Impossible",
        "need to be: Possible 3 2 1 4 ",
        "need to be: Possible 3 2 4 5 1 "
    )

    for (i in tests.indices) {
        val result = solution(tests[i].toCharArray())
        println(results[i])
        printResult(result)
        println()
    }
}

fun printResult(result: Array<Int>) {
    if (result.isEmpty()) {
        println("Impossible")
        return
    }

    println("Possible")
    for (i in 0 until count)
    print("${result[i]} ")
}

var count: Int = 0

fun solution(charArray: CharArray): Array<Int> {
    //A - Z  65 - 90
    //a - z  97 - 122

    //можно прибавить к char 32 и получить номер lower case чара
    val array = Array(100000) {0}
    val stack: Stack<Pair<Char, Int>> = Stack()
    var animalCount = 0
    var cageCount = 0
    var previousChar: Char = 48.toChar()

    for (char in charArray) {
        if (char <= 90.toChar()) {//ловушка
            cageCount++
            //если ловушка подходит для предыдущего элемента (животного)
            if (previousChar >= 97.toChar() && previousChar - 32 == char) {
                val animal = stack.pop()//достаём животное оттуда
                previousChar = if (stack.empty()) '-' else  stack.peek().first
                array[cageCount - 1] = animal.second
                continue
            }
        } else {//животное
            animalCount++
            //если животное подходит для предыдущего элемента (ловушки)
            if (previousChar <= 90.toChar() && previousChar + 32 == char) {
                val cage = stack.pop()//достаём ловушку оттуда
                previousChar = if (stack.empty()) '-' else  stack.peek().first
                array[cage.second - 1] = animalCount
                continue
            }
        }
        //если не подходит
        previousChar = char
        if (char <= 90.toChar()) stack.push(Pair(char, cageCount))
        else stack.push(Pair(char, animalCount))
    }

    if (!stack.empty()) return Array(0){0}

    count = animalCount
    return array
}
