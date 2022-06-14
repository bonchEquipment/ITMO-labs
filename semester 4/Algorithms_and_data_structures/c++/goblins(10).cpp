/**
 * https://contest.yandex.ru/contest/35179/problems/J   /
 */

#include <iostream>

using namespace std;

struct Node {
    int data;
    struct Node *next;
};

//Node 8 -> Node 9
//Node 8 -> Node 9 -> Node new
void push_back(struct Node **tail, int data, struct Node **middle, struct Node **head, int size) {
    auto *newNode = new Node;
    newNode->data = data;
    if (size != 0)
        (*tail)->next = newNode;

    (*tail) = newNode;
    if (size == 0) {
        (*middle) = newNode;
        (*head) = newNode;
    }

    if (size % 2 == 0 && size > 0)
        (*middle) = (*middle)->next;
}

//Node 8 -> Node 9
//Node 8 -> Node new -> Node 9
void insertInCenter(struct Node **middle, int value,  int size) {
    struct Node *next = (*middle)->next;//Node 9
    auto *new_element = new Node;//Node new
    new_element->data = value;
    new_element->next = next;
    (*middle)->next = new_element;

    if (size % 2 == 0)
        *middle = new_element;
}

//Node 1 -> Node 2 -> Node 3
//Node 2 -> Node 3
int pop_front(struct Node **head, struct Node **middle, int size, struct Node **tail) {
    int data = (*head)->data;
    struct Node *secondNode = (*head)->next;
    (*head) = secondNode;

    if (size % 2 == 0)
        *middle = (*middle)->next;
    if (size == 1) {
        *tail = nullptr;
        *middle = nullptr;
    }


    return data;
}


int stringToInt(const string &s) {
    return stoi(s);
}


int main10() {
    Node *head = nullptr;
    Node *tail = nullptr;
    Node *middle = nullptr;
    string s;
    getline(cin, s);
    getline(cin, s);
    head = new Node();
    head->data = stringToInt(s.substr(1));
    tail = head;
    middle = head;
    int size = 1;

    while (cin) {
        getline(cin, s);
        if (s[0] == '+') {
            push_back(&tail, stringToInt(s.substr(1)), &middle, &head, size);
            size++;
        }


        if (s[0] == '-') {
            cout << pop_front(&head, &middle, size, &tail) << endl;
            size--;
        }

        if (s[0] == '*') {
            if (size > 1)
                insertInCenter(&middle, stringToInt(s.substr(1)), size);
            else
                push_back(&tail, stringToInt(s.substr(1)), &middle, &head, size);
            size++;
        }

    }
}
