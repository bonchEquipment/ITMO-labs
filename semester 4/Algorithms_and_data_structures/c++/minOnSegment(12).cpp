/**
 * https://contest.yandex.ru/contest/35179/problems/L/
 */

#include <iostream>
#include <vector>
#include <queue>



using namespace std;

void swap(vector<pair<int, int>> &vector, size_t i, size_t j) {
    pair<int, int> temp = vector[i];
    vector[i] = vector[j];
    vector[j] = temp;
}


void move_element_up(int index, vector<pair<int, int>> &vector) {
    while (index > 1 && vector[index].second < vector[index / 2].second) {
        swap(vector[index], vector[index / 2]);
        index /= 2;
    }
}

void move_element_down(size_t index, vector<pair<int, int>> &vector) {
    while (vector.size() >= index * 2) {
        size_t indexOfLeftNode = 2 * index;
        size_t indexOfRightNode = (2 * index) + 1;
        size_t i = indexOfRightNode;
        if (indexOfRightNode > vector.size() || vector[indexOfRightNode].second > vector[indexOfLeftNode].second)
            i = indexOfLeftNode;
        if (vector[index].second <= vector[i].second)
            return;
        swap(vector, index, i);
        index = i;
    }
}

pair<int, int> getMin(vector<pair<int, int>> &vector) {
    return vector[1];
}

void deleteMin(vector<pair<int, int>> &vector) {
    vector[1] = vector[vector.size() - 1];
    vector.pop_back();
    move_element_down(1, vector);
}


void build_heap(vector<pair<int, int>> &vector) {
    for (size_t i = vector.size(); i > 0; --i) {
        // move_element_down(i, vector, vector.size());
        move_element_up(i - 1, vector);
    }
}

void addNode(vector<pair<int, int>> &vector, pair<int, int> element) {
    vector.push_back(element);
    move_element_up(vector.size() - 1, vector);
}

int main121() {
    int length, size_of_window, tempNum;
    pair<int, int> tempPair;
    cin >> length >> size_of_window;
    vector<pair<int, int>> heap;

    tempPair = pair(0, -999);//index, value

    heap.push_back(tempPair);

    for (int i = 0; i < size_of_window; i++) {
        cin >> tempNum;
        tempPair = pair(i + 1, tempNum);
        heap.push_back(tempPair);
    }

    build_heap(heap);
// [0 1 2] 3 4

    for (int i = 0; i <= length - size_of_window; i++) {
        while (heap.size() != 1) {
            if (getMin(heap).first - 1 < i)
                deleteMin(heap);
            else {
                cout << getMin(heap).second << " ";
                break;
            }
        }

        if (i != length - size_of_window) {//если не последняя итерация
            cin >> tempNum;
            tempPair = pair(size_of_window + i + 1, tempNum);
            addNode(heap, tempPair);
        }


    }
    return 0;
}

//заменить first на second, если не заработает
int main1212(){
    int length, size_of_window, tempNum;
    pair<int, int> tempPair;
    cin >> length >> size_of_window;
    //vector<pair<int, int>> heap;
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> heap;


//[0 1\ 2] 3 4
    for (int i = 0; i < size_of_window - 1; i++) {
        cin >> tempNum;
        tempPair = pair(tempNum, i);//value, index
        heap.push(tempPair);
    }

    for (int i = size_of_window - 1; i < length; i++) {
        cin >> tempNum;
        tempPair = pair(tempNum, i);
        heap.push(tempPair);

        while (true) {
            if (i - size_of_window >= heap.top().second)
                heap.pop();
            else {
                cout << heap.top().first << " ";
                break;
            }
        }
    }
    return 0;



}
