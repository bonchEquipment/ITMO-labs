/**
 * https://contest.yandex.ru/contest/35179/problems/E/
 */

#include <iostream>
#include <vector>

using namespace std;

bool isAbleToPlaceCows(const vector<int> &boxes, int amountOfCow, int k) {
    int cowsLeft = amountOfCow - 1;
    int coordinateOfLastBox = boxes[0];

    for (int i = 1; i < boxes.size(); i++) {
        if (boxes[i] - coordinateOfLastBox >= k) {
            cowsLeft--;
            coordinateOfLastBox = boxes[i];
        }
    }
    return cowsLeft <= 0;
}

int main3() {
    int amountOfBoxes, amountOfCows;
    cin >> amountOfBoxes >> amountOfCows;
    vector<int> boxesCoordinates;
    for (int i = 0; i < amountOfBoxes; i++) {
        int v;
        cin >> v;
        boxesCoordinates.push_back(v);
    }

    int minK = 0;
    int maxK = boxesCoordinates[amountOfBoxes - 1] - boxesCoordinates[0];

    while (minK + 1 < maxK) {
        if (isAbleToPlaceCows(boxesCoordinates, amountOfCows, (minK + maxK) / 2)) {
            minK = (minK + maxK) / 2;
        } else {
            maxK = (minK + maxK) / 2;
        }
    }

    int result = isAbleToPlaceCows(boxesCoordinates,amountOfCows, maxK) ? maxK : minK;
    cout << result;
    return 0;
}

