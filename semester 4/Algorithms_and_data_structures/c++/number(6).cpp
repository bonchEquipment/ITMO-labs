/**
 * https://contest.yandex.ru/contest/35179/problems/F/
 */

#include <iostream>
#include <cstdlib>
#include <algorithm>
#include <vector>

using namespace std;

void swap(string *array, int firstIndex, int secondIndex) {
    string temp = array[firstIndex];
    array[firstIndex] = array[secondIndex];
    array[secondIndex] = temp;
}

int compareStringsWithEqualLength(const string &a, const string &b) {
    const char *aCharArray = a.c_str();
    const char *bCharArray = b.c_str();

    for (int i = 0; i < a.length(); i++)
        if (aCharArray[i] < bCharArray[i]) return -1;
        else if (aCharArray[i] > bCharArray[i]) return 1;

    return 0;
}

int compare(string a, string b) {
    const char firstLetterOfA = a[0];
    const char firstLetterOfB = b[0];
    const int  lengthDifference = a.length() - b.length();
    for (int i = 0; i < abs(lengthDifference); i++) {
        if (lengthDifference > 0)
            b += firstLetterOfB;
        else if (lengthDifference < 0)
            a += firstLetterOfA;
    }

    return compareStringsWithEqualLength(a, b);
}

/*int compare(const void *a, const void *b) {
    return compare(*(string *) a, *(string *) b);
}*/

void quickSort(string *array, int lowIndex, int highIndex) {
    if (lowIndex >= highIndex) return;

    const string pivot = array[highIndex];
    int leftPointer = lowIndex;
    int rightPointer = highIndex - 1;

    while (leftPointer < rightPointer) {
//while (rawArray[leftPointer] <= pivot);
        while (compare(pivot, array[leftPointer]) >= 0 && leftPointer < rightPointer)
            leftPointer++;
//while (rawArray[rightPointer] >= pivot)
        while (compare(pivot, array[rightPointer]) <= 0 && leftPointer < rightPointer)
            rightPointer--;

        swap(array, leftPointer, rightPointer);
    }


    if (compare(array[leftPointer], array[highIndex]) > 0)
        swap(array, leftPointer, highIndex);
    else
        leftPointer = highIndex;

    quickSort(array, lowIndex, leftPointer - 1);
    quickSort(array, leftPointer + 1, highIndex);
}

bool comp(string &a, string &b){
    //return compare(a, b) < 0;
    return a + b < b + a;
}

int main6() {
   // int countOfNumbers = 4;
    int countOfNumbers = 0;
   // string array[100] = {"2", "20", "004", "66"};
    //string array[100];
    vector<string> v;

  /*  for (int i = 0; i < 4; i++){
        a.push_back(array[i]);
    }*/

    string s;
     while (cin >> s) {
        v.push_back(s);
        countOfNumbers++;
    }


   // sort(array->begin(), array->end(), comp);
    sort(v.begin(), v.end(), comp);

   /* while (cin) {
        cin >> array[countOfNumbers];
        countOfNumbers++;
    }*/

    //quickSort(array, 0, countOfNumbers - 1);
    //size_t size = sizeof(array) / sizeof (char*);
   // qsort(array, size, sizeof(char*), compare);
    for (int i = 0; i < countOfNumbers; i++){
        s = v[countOfNumbers - 1 - i];
        cout << s;
    }
    return 0;
      //  cout << array[countOfNumbers - 1 - i];

}