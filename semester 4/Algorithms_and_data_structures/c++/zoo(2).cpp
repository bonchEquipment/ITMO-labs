/**
 * https://contest.yandex.ru/contest/35179/problems/B/
 */

#include <iostream>
#include <stack>

using namespace std;

int main2() {
    int array[100000];
    stack<pair<char, int>> animalsAndCages;
    int animalCount = 0;
    int cageCount = 0;
    char previousChar = 48; //не буква
    char currentChar = 48;

    while ((currentChar = getchar()) != 10) {
        if (currentChar <= 90) {//ловушка
            cageCount++;
            //если ловушка подходит для предыдущего элемента (животного)
            if (previousChar >= 97 && previousChar - 32 == currentChar) {
                pair<char, int> animal = animalsAndCages.top();//достаём животное оттуда
                animalsAndCages.pop();
                previousChar = animalsAndCages.empty() ? '-' : animalsAndCages.top().first;
                array[cageCount - 1] = animal.second;
                continue;
            }
        } else {//животное
            animalCount++;
            //если животное подходит для предыдущего элемента (ловушки)
            if (previousChar <= 90 && previousChar + 32 == currentChar) {
                pair<char, int> cage = animalsAndCages.top();//достаём ловушку оттуда
                animalsAndCages.pop();
                previousChar = animalsAndCages.empty() ? '-' : animalsAndCages.top().first;
                array[cage.second - 1] = animalCount;
                continue;
            }
        }
        //если не подходит
        previousChar = currentChar;
        if (currentChar <= 90) animalsAndCages.push(pair(currentChar, cageCount));
        else animalsAndCages.push(pair(currentChar, animalCount));
    }

    if (!animalsAndCages.empty()) {
        printf("Impossible");
        return 0;
    }

    printf("Possible\n");

    for (int i = 0; i < animalCount; i++){
        printf("%d ", array[i]);
    }


}