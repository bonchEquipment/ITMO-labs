/**
 * https://contest.yandex.ru/contest/35179/problems/A/
 */ 
#include <stdio.h>

void count(int* flowers, int size);


int main1() {
    int size;
    scanf("%d", &size);
    int flowers[size];

    for (int i = 0; i < size; i++) scanf("%d", &flowers[i]);

    if (size < 3){
        printf("%d %d\n", 1, size);
        return 0;
    }
    int startIndexOfMax = 0;
    int lengthOfMax = 2;
    int startIndexOfCur = 0;
    int lengthOfCur = 2;

    for (int i = 2; i < size; i++){
        if (flowers[i] == flowers[i - 1] && flowers [i - 2] == flowers[i]){
            startIndexOfCur = i - 1;
            lengthOfCur = 2;
            continue;
        }
        lengthOfCur++;

        if (lengthOfCur > lengthOfMax) {
            startIndexOfMax = startIndexOfCur;
            lengthOfMax = lengthOfCur;
        }

    }

    printf("%d %d\n", startIndexOfMax + 1, startIndexOfMax + lengthOfMax);


   int flowers1[6] = {5, 6, 6, 6, 23, 9};
    printf("should return: 3 6|");
    count(flowers1, 6);

    int flowers2[1] = {5};
    printf("should return: 1 1|");
    count(flowers2, 1);

    int flowers3[2] = {2, 2};
    printf("should return: 1 2|");
    count(flowers3, 2);

    int flowers4[3] = {2, 2, 2};
    printf("should return: 1 2|");
    count(flowers4, 3);

    int flowers5[4] = {2, 2, 2, 2};
    printf("should return: 1 2|");
    count(flowers5, 4);

    return 0;
}

void count(int* flowers, int size){
    if (size < 3){
        printf("%d %d\n", 1, size);
        return;
    }
    int startIndexOfMax = 0;
    int lengthOfMax = 2;
    int startIndexOfCur = 0;
    int lengthOfCur = 2;

    for (int i = 2; i < size; i++){
        if (flowers[i] == flowers[i - 1] && flowers [i - 2] == flowers[i]){
            startIndexOfCur = i - 1;
            lengthOfCur = 2;
            continue;
        }
        lengthOfCur++;

        if (lengthOfCur > lengthOfMax) {
            startIndexOfMax = startIndexOfCur;
            lengthOfMax = lengthOfCur;
        }

    }

    printf("%d %d\n", startIndexOfMax + 1, startIndexOfMax + lengthOfMax);
}

