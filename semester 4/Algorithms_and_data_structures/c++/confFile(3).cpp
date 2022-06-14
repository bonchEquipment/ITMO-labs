/**
 * https://contest.yandex.ru/contest/35179/problems/C/
 */

#include <iostream>
#include <fstream>
#include <vector>
#include <map>
#include <stack>
#include <list>
#include <regex>
#include <set>
#include <unordered_map>

using namespace std;

void solution(const string& line);

int stringToInt(const string &s) {
    return stoi(s);
}

bool contains(const string &s, char c) {
    return s.find(c) != -1;
}

static unordered_map<string, int> variables;
static stack<unordered_map<string, int>> changedVariables;

int main1() {
    changedVariables.push(unordered_map<string, int>());

    string input_line;

    while(cin) {
        getline(cin, input_line);
        solution(input_line);
    }

}

void doCloseBracerAction() {
    unordered_map m = changedVariables.top();
    for (const auto &pair1: m)
        variables[pair1.first] = m[pair1.first];
    changedVariables.pop();
}

void doOpenBracerAction() {
    changedVariables.push(unordered_map<string, int>());
}

void doAssignLineAction(const string &line) {
    unsigned long long int indexOfEqualSign = line.find('=');
    string variable = line.substr(0, indexOfEqualSign);
    string value = line.substr(indexOfEqualSign + 1, line.size());
    bool isValueANumber = isdigit(value[0]) || contains(value, '-');

    int numericValue;

    if (isValueANumber)
        numericValue = stringToInt(value);
    else if (variables.count(value)) {
        numericValue = variables[value];//если это уже объявленная переменная - просто достаем её
    } else {
        variables[value] = 0; //если новая - объявляем и присваиваем 0
        numericValue = 0;
    }

    if (!variables.count(variable)) //если элемент встречается первый раз
        changedVariables.top()[variable] = 0;
    else if (!changedVariables.top().count(variable))
        changedVariables.top()[variable] = variables[variable]; //если существующий элемент первый раз за скобку

    variables[variable] = numericValue;
    if (!isValueANumber) cout << (numericValue) << endl;
}

void solution(const string& line) {
    if (line == "{") doOpenBracerAction();
    if (line == "}") doCloseBracerAction();
    if (contains(line, '=')) doAssignLineAction(line);
}