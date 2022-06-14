/**
https://contest.yandex.ru/contest/35179/problems/D/
 */
package agronom

fun main(){

    val test1 = "1 3 1 5 2"
    val array = test1.split(" ").map { item -> item.toLong() }
    val res = solution(array[0], array[1], array[2], array[3], array[4])

    println("Res is: $res")
}


fun solution(initialAmount: Long, multiplier: Long, bacteriaForExperiments: Long, containerSize: Long, days: Long): Long{
    var res = amountAfterDay(initialAmount, multiplier, bacteriaForExperiments, containerSize)
    for (i in 1..days){
        res = amountAfterDay(res, multiplier, bacteriaForExperiments, containerSize)
        if (res <= 0) return 0
    }

    return res;
}

fun amountAfterDay(initialAmount: Long, multiplier: Long, bacteriaForExperiments: Long, containerSize: Long): Long{
    val amountAfterMultiplying = initialAmount * multiplier
    var amountAfterExperiments = 0L

    if (amountAfterMultiplying > bacteriaForExperiments)
        amountAfterExperiments = amountAfterMultiplying - bacteriaForExperiments

    if (amountAfterExperiments > containerSize) return containerSize
    return amountAfterExperiments
}