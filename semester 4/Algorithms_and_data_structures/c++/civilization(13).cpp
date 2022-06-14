/**
 * https://contest.yandex.ru/contest/35179/problems/M/
 */
#include <iostream>
#include <cmath>
#include <vector>
#include "unordered_map"
#include "list"
#include <algorithm>

using namespace std;

struct Node {
    int x = -1;
    int y = -1;
    int weight = 0;
    int parentX = -1;
    int parentY = -1;
    char type = 0;
    bool checked = false;
};

struct Node createEmptyNode() {
    struct Node node = Node();
    node.weight = INT32_MAX;
    node.type = -1;
    return node;
}

struct Node createNode(int x, int y, int value, int parentX, int parentY, char type) {
    struct Node node = Node();
    node.parentX = parentX;
    node.parentY = parentY;
    node.weight = value;
    node.type = type;
    node.x = x;
    node.y = y;
    return node;
}

struct pair_hash {
    template<class T1, class T2>
    std::size_t operator()(const std::pair<T1, T2> &pair) const {
        return std::hash<T1>()(pair.first) ^ std::hash<T2>()(pair.second);
    }
};

struct Node emptyNode = createEmptyNode();

bool isEmptyNode(struct Node node) {
    return node.type == emptyNode.type;
}


void printPath(vector<vector<struct Node>> &nodes, struct Node &startPoint,
               struct Node &endPoint) {
    auto currentNode = startPoint;
    auto parent = nodes[startPoint.parentY][startPoint.parentX];
    int weight = currentNode.weight;
    if (endPoint.type > startPoint.type) weight++;
    if (endPoint.type < startPoint.type) weight--;
    cout << weight << endl;

    while (true) {
        if (parent.x > currentNode.x) cout << 'E';
        if (parent.x < currentNode.x) cout << 'W';
        if (parent.y > currentNode.y) cout << 'S';
        if (parent.y < currentNode.y) cout << "N";
        currentNode = parent;
        if (parent.parentX == -1)
            break;
        parent = nodes[parent.parentY][parent.parentX];
    }
}


struct Node *
findLowestCostNode(unordered_map<pair<int, int>, struct Node *, pair_hash> &nodes) {
    int minValue = INT32_MAX;
    struct Node *minValueNode = &emptyNode;
    struct Node *tempNode;
    for (auto it = nodes.begin(); it != nodes.end(); ++it) {
        tempNode = nodes[it->first];
        if (tempNode->weight < minValue && !(tempNode->checked)) {
            minValueNode = tempNode;
            minValue = minValueNode->weight;
        }
    }
    return minValueNode;
}


bool isParent(struct Node &child, struct Node &parent) {
    return (child.parentX == parent.x && child.parentY == parent.y);
}

list<struct Node *>
findNeighbors(struct Node &node, vector<vector<struct Node>> &nodes, int maxX, int maxY) {
    list<struct Node *> l;
    struct Node *tempNode;
    int x = node.x;
    int y = node.y;

    if (y != 0) {
        tempNode = const_cast<struct Node *>(&nodes[y - 1][x]);
        if (tempNode->type != '#' && !isParent(node, *tempNode) && !tempNode->checked)
            l.push_back(tempNode);
    }
    if (x != maxX) {
        tempNode = const_cast<struct Node *>(&nodes[y][x + 1]);
        if (tempNode->type != '#' && !isParent(node, *tempNode) && !tempNode->checked)
            l.push_back(tempNode);
    }
    if (y != maxY) {
        tempNode = const_cast<struct Node *>(&nodes[y + 1][x]);
        if (tempNode->type != '#' && !isParent(node, *tempNode) && !tempNode->checked)
            l.push_back(tempNode);
    }
    if (x != 0) {
        tempNode = const_cast<struct Node *>(&nodes[y][x - 1]);
        if (tempNode->type != '#' && !isParent(node, *tempNode) && !tempNode->checked)
            l.push_back(tempNode);
    }
    return l;
}


void findShortestWay(vector<vector<struct Node>> &nodes,
                     struct Node &startPoint, struct Node &endPoint, int maxX, int maxY) {

    unordered_map<pair<int, int>, struct Node *, pair_hash> nodesToCheck;
    nodesToCheck[pair(startPoint.x, startPoint.y)] = &startPoint;

    list<struct Node *> neighbors;
    int cost, destinationCost;
    struct Node *node = findLowestCostNode(nodesToCheck);
    while (!isEmptyNode(*node)) {
        destinationCost = endPoint.weight;
        cost = node->weight;
        if (cost >= destinationCost) break;

        neighbors = findNeighbors(*node, nodes, maxX, maxY);
        for (struct Node *neighbor: neighbors) {
            int costOfMove = (neighbor->type == '.') ? 1 : 2;
            if (cost + costOfMove < neighbor->weight) {
                neighbor->weight = cost + costOfMove;
                neighbor->parentX = node->x;
                neighbor->parentY = node->y;
            }
            if (!(neighbor->checked))
                nodesToCheck[pair(neighbor->x, neighbor->y)] = neighbor;
        }
        nodes[node->y][node->x].checked = true;
        nodesToCheck.erase(pair(node->x, node->y));
        node = findLowestCostNode(nodesToCheck);
    }

    if (endPoint.parentX == -1) {
        cout << "-1";
        return;
    }
    printPath(nodes, endPoint, startPoint);
}

int main131() {
    int heightOfMap, widthOfMap, startY, startX, endY, endX;
    char type;
    cin >> heightOfMap >> widthOfMap >> startY >> startX >> endY >> endX;
    vector<vector<struct Node>> nodes(heightOfMap, vector<struct Node>(widthOfMap, createEmptyNode()));
    for (int i = 0; i < heightOfMap; i++)
        for (int j = 0; j < widthOfMap; j++) {
            cin >> type;
            nodes[i][j] = createNode(j, i, INT32_MAX, -1, -1, type);
        }

    nodes[endY - 1][endX - 1].weight = 0;
    findShortestWay(nodes, nodes[endY - 1][endX - 1], nodes[startY - 1][startX - 1], widthOfMap - 1, heightOfMap - 1);
}