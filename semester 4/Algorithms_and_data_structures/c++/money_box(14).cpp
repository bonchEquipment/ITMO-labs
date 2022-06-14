/**
 * https://contest.yandex.ru/contest/35179/problems/N/
 */
#include <iostream>
#include <vector>
using namespace std;

vector<int> getInput(int number_of_boxes){
    int box;
    vector<int> v;
    for (int i = 0; i < number_of_boxes; i++) {
        cin >> box;
        v.push_back(box - 1);
    }
    return v;
}

void process(vector<int>& v){
    int number_of_boxes = v.size();
    bool isChanged = true;
    while (isChanged) {
        isChanged = false;
        for (int i = 0; i < number_of_boxes; i++)
            if (v[i] != v[v[i]]) {
                v[i] = v[v[i]];
                isChanged = true;
            }
    }
}


int main() {
    int number_of_boxes;
    cin >> number_of_boxes;
    vector<int> v = getInput(number_of_boxes);
    process(v);
    int res = 0;
    for (int k = 0; k < number_of_boxes; k++)
        if (v[k] == k) res = res + 1;
    cout << res;
}