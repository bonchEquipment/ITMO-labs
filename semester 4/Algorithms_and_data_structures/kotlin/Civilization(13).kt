/**
*https://contest.yandex.ru/contest/35179/problems/M/
*/

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.min

const val dotValue = 1
const val wValue = 2
const val sharpValue = 3
val emptyNode =  Node(Pair(0, 0), Int.MAX_VALUE, null, 0)


fun main() {
    val scanner = Scanner(System.`in`)
    val heightOfMap = scanner.nextInt()
    val widthOfMap = scanner.nextInt()
    val startY = scanner.nextInt()
    val startX = scanner.nextInt()
    val endY = scanner.nextInt()
    val endX = scanner.nextInt()
    val map = Array(heightOfMap) { CharArray(widthOfMap) }
    scanner.nextLine()
    val nodes = HashMap<Pair<Int, Int>, Node>()

    for (i in 0 until heightOfMap)
        map[i] = scanner.nextLine().toCharArray()

    for (i in 0 until heightOfMap)
        for (j in 0 until widthOfMap){
            val type = charToValue(map[i][j])
            nodes[Pair(j, i)] = Node(Pair(j, i), Int.MAX_VALUE, null, type)
        }

    printMap(map)
    val startPoint = Pair(startX-1, startY-1)
    val endPoint = Pair(endX-1, endY-1)

    nodes[endPoint]!!.weight = 0
    val solution = findShortestWay(nodes, startPoint)
    println(solution.first +  nodes[endPoint]!!.type - nodes[startPoint]!!.type)
    println(solution.second)
}


fun findShortestWay(nodes: HashMap<Pair<Int, Int>, Node>, destination: Pair<Int, Int>): Pair<Int, String> {
    val checked = LinkedList<Pair<Int, Int>>()
    var node = findLowestCostNode(nodes, checked)
    while (node != emptyNode){
        val cost = node.weight
        val neighbors = findNeighbors(node, nodes)
        for (neighbor in neighbors){
            val costOfMove = if (neighbor.type == dotValue) 1 else 2
            if (cost + costOfMove < neighbor.weight){
                neighbor.weight = cost + costOfMove
                neighbor.parent = node.coordinates
            }
        }
        checked.add(node.coordinates)
        node = findLowestCostNode(nodes, checked)
    }
    val res = findStringPath(nodes, nodes[destination]!!)
    return Pair(nodes[destination]!!.weight, res)
}

fun findLowestCostNode(nodes:  HashMap<Pair<Int, Int>, Node>, checked: LinkedList<Pair<Int, Int>>):Node {
    var minValue = Int.MAX_VALUE
    var minValueNode = emptyNode
    for (i in nodes.keys)
        if (nodes[i]!!.weight < minValue && !checked.contains(i)){
            minValue = nodes[i]!!.weight
            minValueNode = nodes[i]!!
        }
    return minValueNode
}

fun findStringPath(nodes: HashMap<Pair<Int, Int>, Node>, node: Node): String{
    var currentNode = node
    var parent = nodes[node.parent]

    var path = ""
    while(parent != null){
        val parentX = parent.coordinates.first
        val parentY = parent.coordinates.second
        val x = currentNode.coordinates.first
        val y = currentNode.coordinates.second
        if (parentX > x) path += "E"
        if (parentX < x) path += "W"
        if (parentY > y) path += "S"
        if (parentY < y) path += "N"
        currentNode = parent
        parent = nodes[parent.parent]
    }
    return path
}


fun findNeighbors(node: Node, nodes: HashMap<Pair<Int, Int>, Node>):LinkedList<Node>{
    val array: Array<Node?> = Array(4) {emptyNode}
    val list = LinkedList<Node>()
    val x = node.coordinates.first
    val y = node.coordinates.second

    array[0] = nodes[Pair(x, y - 1)]
    array[1] = nodes[Pair(x+1, y)]
    array[2] = nodes[Pair(x, y + 1)]
    array[3] = nodes[Pair(x-1, y)]

    for (i in 0..3)
        if (array[i] != null && array[i]!!.type != sharpValue && array[i]!!.coordinates != node.parent)
            list.add(array[i]!!)

    return list
}

class Node(var coordinates: Pair<Int, Int>, var weight: Int, var parent: Pair<Int, Int>?, var type: Int){
    override fun toString(): String{
        return "$coordinates ${valueToChar(type)} $weight"
    }
}

fun charToValue(char: Char): Int{
    if (char == '.') return dotValue
    if (char == 'W') return wValue
    if (char == '#') return sharpValue
    throw Exception("Unexpected value $char")
}


fun valueToChar(value: Int): Char{
    if (value == dotValue) return '.'
    if (value == wValue) return 'W'
    if (value == sharpValue) return '#'
    throw Exception("Unexpected value $value")
}

fun printMap(map: Array<CharArray>){
    for (i in map.indices){
        println()
        for (j in 0 until map[i].size)
            print(map[i][j])
    }
    println()
}
