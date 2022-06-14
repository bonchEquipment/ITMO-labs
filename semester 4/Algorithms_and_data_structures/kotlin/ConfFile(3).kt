/**
 * https://contest.yandex.ru/contest/35179/problems/C/
 * @Конфигурационный_файл
 * *****************
 *@Формат_ввода
 *Ограничение времени	1 секунда
 *Ограничение памяти	64Mb
 *Ввод	стандартный ввод или input.txt
 *Вывод	стандартный вывод или output.txt

 *@Условия
 *Вадим разрабатывает парсер конфигурационных файлов для своего проекта. Файл состоит из блоков, которые выделяются с помощью символов «{» — начало блока, и «}» — конец блока. Блоки могут вкладываться друг в друга. В один блок может быть вложено несколько других блоков.
В конфигурационном файле встречаются переменные. Каждая переменная имеет имя, которое состоит из не более чем десяти строчных букв латинского алфавита. Переменным можно присваивать числовые значения. Изначально все переменные имеют значение 0.
Присваивание нового значения записывается как <variable>=<number>, где <variable> — имя переменной, а <number> — целое число, по модулю не превосходящее 109. Парсер читает конфигурационный файл построчно. Как только он встречает выражение присваивания, он присваивает новое значение переменной. Это значение сохраняется до конца текущего блока, а затем восстанавливается старое значение переменной. Если в блок вложены другие блоки, то внутри тех из них, которые идут после присваивания, значение переменной также будет новым.
Кроме того, в конфигурационном файле можно присваивать переменной значение другой переменной. Это действие записывается как <variable1>=<variable2>. Прочитав такую строку, парсер присваивает текущее значение переменной variable2 переменной variable1. Как и в случае присваивания константного значения, новое значение сохраняется только до конца текущего блока. После окончания блока переменной возвращается значение, которое было перед началом блока.
Для отладки Вадим хочет напечатать присваиваемое значение для каждой строки вида <variable1>=<variable2>. Помогите ему отладить парсер.
 *

 *@Формат_ввода
Входные данные содержат хотя бы одну и не более 105 строк. Каждая строка имеет один из четырех типов:
{ — начало блока;
} — конец блока;
<variable>=<number> — присваивание переменной значения, заданного числом;
<variable1>=<variable2> — присваивание одной переменной значения другой переменной. Переменные <variable1> и <variable2> могут совпадать.
Гарантируется, что ввод является корректным и соответствует описанию из условия. Ввод не содержит пробелов.

 *@Формат_вывода
Для каждой строки типа <variable1>=<variable2> выведите значение, которое было присвоено.
 */
package agronom

import java.util.*
import kotlin.collections.HashMap


fun main() {
    val test1 = "a=b\n" +
            "b=123\n" +
            "var=b\n" +
            "b=-34\n" +
            "{\n" +
            "c=b\n" +
            "b=1000000000\n" +
            "d=b\n" +
            "{\n" +
            "a=b\n" +
            "e=var\n" +
            "}\n" +
            "}\n" +
            "b=b"
    solution2(test1)

}

fun solution2(str: String) {

    val variables: HashMap<String, Int> = HashMap()

    val changedVariables: Stack<HashMap<String, Int>> = Stack()

    changedVariables.push(HashMap())

    val lines = str.split("\n")

    for (line in lines)
        when {
            line == "{" -> changedVariables.push(HashMap())

            line == "}" -> {
                val map = changedVariables.pop()
                for (variableName in map.keys)
                    variables[variableName] = map[variableName]!!
            }

            line.contains(Regex("=")) -> {
                val symbols = line.split("=")
                val variable = symbols[0]
                val value = symbols[1]
                val isValueANumber: Boolean = value.contains(Regex("[0-9]"))
                val topOfStack = changedVariables.peek()

                val numericValue: Int
                if (isValueANumber)
                    numericValue = value.toInt()
                else if (variables.containsKey(value)){
                    //если это уже объявленная переменная - просто достаем её
                    numericValue = variables[value]!!
                } else {
                    //если новая - объявляем и присваиваем 0
                    variables[value] = 0
                    numericValue = 0
                }

                if (!variables.containsKey(variable))
                //если элемент новый новый
                    topOfStack[variable] = 0
                else if (!changedVariables.peek().containsKey(variable))
                //если старый элемент первый раз за скобку
                    topOfStack[variable] = variables[variable]!!

                //если элемент старый старый
                variables[variable] = numericValue
                if (!isValueANumber) println(numericValue)

            }


        }
}