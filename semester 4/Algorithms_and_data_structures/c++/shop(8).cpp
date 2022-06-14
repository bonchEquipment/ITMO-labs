/**
 * https://contest.yandex.ru/contest/35179/problems/H/
 */
#include <iostream>

using namespace std;

void swap(int *array, int firstIndex, int secondIndex) {
    int temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
}

void quickSort(int *array, int lowIndex, int highIndex) {
    if (lowIndex >= highIndex) return;

    const int pivot = array[highIndex];
    int leftPointer = lowIndex;
    int rightPointer = highIndex - 1;

    while (leftPointer < rightPointer) {
//while (rawArray[leftPointer] <= pivot);
        while (array[leftPointer] <= pivot && leftPointer < rightPointer)
            leftPointer++;
//while (rawArray[rightPointer] >= pivot)
        while (array[leftPointer] >= pivot && leftPointer < rightPointer)
            rightPointer--;

        swap(array, leftPointer, rightPointer);
    }


    if (array[leftPointer] > array[highIndex])
        swap(array, leftPointer, highIndex);
    else
        leftPointer = highIndex;

    quickSort(array, lowIndex, leftPointer - 1);
    quickSort(array, leftPointer + 1, highIndex);
}

int compare(const void *a, const void *b) {
    return (*(int *) a - *(int *) b);
}

int main8() {
    int productCount, saleRate, saleCount = 0;
    cin >> productCount;
    cin >> saleRate;
    int productsCost[productCount];

    for (int i = 0; i < productCount; i++) {
        cin >> productsCost[i];
    }

   // quickSort(productsCost, 0, productCount - 1);
    qsort(productsCost, productCount, sizeof(int), compare);

    long amountToPay = 0;

    for (int i = productCount - 1; i >= 0; i--) {
        saleCount++;
        if (saleCount % saleRate == 0) continue;
        amountToPay += productsCost[i];
    }

    cout << amountToPay;
}
