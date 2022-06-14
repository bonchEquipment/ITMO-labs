/**
 * https://contest.yandex.ru/contest/35179/problems/E/
 * @Коровы_в_стойла
 * *****************
 *@Формат_ввода
 *Ограничение времени	0.1 секунда
 *Ограничение памяти	256Mb
 *Ввод	стандартный ввод или input.txt
 *Вывод	стандартный вывод или output.txt

 *@Условия
 *На прямой расположены стойла, в которые необходимо расставить коров так, чтобы минимальное
 *расcтояние между коровами было как можно больше.

 *@Формат_ввода
 *В первой строке вводятся числа N (2 < N ≤ 10^5) – количество стойл и K (1 < K < N ) – количество коров.
 *Во второй строке задаются N натуральных чисел в порядке возрастания – координаты стойл
 *(координаты не превосходят 10^9).

 *@Формат_вывода
  Выведите одно число – наибольшее возможное допустимое расстояние.
*/
package agronom

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val amountOfBoxes = scanner.nextInt()
    val amountOfCows = scanner.nextInt()
    val boxesCoordinates = Array<Int>(amountOfBoxes) { 0 }

    for (i in boxesCoordinates.indices)
        boxesCoordinates[i] = scanner.nextInt()

    var minK = 0
    var maxK = boxesCoordinates[amountOfBoxes - 1] - boxesCoordinates[0];

    while (minK + 1 < maxK) {
        if (isAbleToPlaceCows(boxesCoordinates, amountOfCows, (minK + maxK) / 2))
            minK = (minK + maxK) / 2
        else
            maxK = (minK + maxK) / 2
    }

    val result = if (isAbleToPlaceCows(boxesCoordinates, amountOfCows, maxK)) maxK else minK
    println(result)
}


fun isAbleToPlaceCows(boxes: Array<Int>, amountOfCow: Int, K: Int): Boolean {
    var cowsLeft = amountOfCow - 1
    var coordinateOfLastBox = boxes[0]

    for (i in 1 until boxes.size) {
        if (boxes[i] - coordinateOfLastBox >= K) {
            cowsLeft--
            coordinateOfLastBox = boxes[i]
        }
    }
    return cowsLeft <= 0
}


fun solution1(boxes: Array<Pair<Int, Boolean>>, amountOfBoxes: Int, amountOfCows: Int): Int {
    boxes[0] = Pair(boxes[0].first, true)
    boxes[amountOfBoxes - 1] = Pair(boxes[amountOfBoxes - 1].first, true)
    for (i in 0 until amountOfCows - 2) {//-2 так как 2 мы уже разметили
        boxes[i + 1] = Pair(boxes[i + 1].first, true) // загоняем коров в первые стойла
    }

    for (i in 0..amountOfBoxes - amountOfCows - 2) {
        //дать каждой корове возможность шагнуть
    }


    return countMinDistance(boxes, amountOfCows)
}


fun countMinDistance(boxes: Array<Pair<Int, Boolean>>, amountOfCows: Int): Int {
    var cowsLeft = amountOfCows - 2
    var minDistance = boxes[boxes.size - 1].first - boxes[0].first
    var previousCowCord = boxes[0].first

    for (i in 1..boxes.size - 2) {
        if (boxes[i].second) {
            var distance = boxes[i].first - previousCowCord
            previousCowCord = boxes[i].first
            if (boxes[i].first < minDistance) minDistance = boxes[i].first
            if (cowsLeft-- == 0) break
        }
    }

    return minDistance
}
