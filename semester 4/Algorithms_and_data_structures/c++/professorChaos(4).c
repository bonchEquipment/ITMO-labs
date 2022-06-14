/**
 * https://contest.yandex.ru/contest/35179/problems/D/
 */
#include <stdio.h>

long long solution(long long initialAmount, int multiplier, int bacteriaForExperiments, int containerSize, long long days);

int main() {

    FILE* file = fopen ("chaos.in", "r");
    int params[3];
    long long initialAmount;
    long long days;
    fscanf(file, "%lld %d %d %d %lld", &initialAmount, &params[0], &params[1], &params[2], &days);

    long long res = solution(initialAmount ,params[0], params[1], params[2], days);

    printf("%lld", res);
    return 0;
}

long long amountAfterDay(long long initialAmount, int multiplier, int bacteriaForExperiments, int containerSize){
    long long  amountAfterMultiplying = initialAmount * multiplier;
    long long amountAfterExperiments = 0L;

    if (amountAfterMultiplying > bacteriaForExperiments)
        amountAfterExperiments = amountAfterMultiplying - bacteriaForExperiments;

    if (amountAfterExperiments > containerSize) return containerSize;
    return amountAfterExperiments;
}

long long solution(long long initialAmount, int multiplier, int bacteriaForExperiments, int containerSize, long long days){
    if (days == 0 || days < 0) return initialAmount;
    long long res = amountAfterDay(initialAmount, multiplier, bacteriaForExperiments, containerSize);
    for (int i = 1; i < days && i < 10000000; i++){
        res = amountAfterDay(res, multiplier, bacteriaForExperiments, containerSize);
        if (res <= 0) return 0;
    }

    return res;
}