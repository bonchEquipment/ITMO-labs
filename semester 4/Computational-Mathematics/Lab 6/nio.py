from functions import *


COUNT_OF_FUNCTIONS = len(list_of_functions)


def ask_function_num():
    function_num = -33
    while not is_in_range(function_num, COUNT_OF_FUNCTIONS):
        for i in range(0, COUNT_OF_FUNCTIONS):
            print(str(i) + ") " + "y` = " + get_function_name(i))
        try:
            function_num = int(input("Введите номер желаемой функции: "))
        except ValueError:
            pass
    return function_num


def ask_input_source():
    mode = 0
    while mode != 1 and mode != 2:
        try:
            mode = int(input("Чтобы ввести точки нажмите 1, чтобы использовать готовую функцию 2: "))
        except ValueError:
            pass
    return mode


def ask_for_argument():
    while True:
        try:
            argument = float(input("Введите аргумент: "))
            return argument
        except ValueError:
            print("Аргумент должен быть числом")



def ask_count_of_numbers():
    while True:
        try:
            count = int(input("Укажите колличество точек, которое собираетесь ввести: "))
            if count > 0:
                return count
        except ValueError:
            pass


def ask_for_points(count):
    string = input("Введите точки через пробел (1,2 5,3)\n")
    array_of_dots = string.split(' ')
    numbers = []
    for i in range(count):
        temp = array_of_dots[i].split(',')
        numbers.append((float(temp[0]), float(temp[1])))
    print(f'Введенные точки: {numbers}')
    return numbers


def is_in_range(number, row):
    for i in range(row):
        if i == number:
            return True
    return False


def ask_number(message):
    while True:
        try:
            count = float(input(f'{message}  '))
            return count
        except ValueError:
            pass


def ask_x0():
    return ask_number("введите x0")


def ask_y0(num):
    return ask_number(f'y({num}) = ')


def ask_a():
    return ask_number("Введите левую границу (a)")


def ask_b():
    return ask_number("Введите правую границу (b)")


def ask_h():
    return ask_number("Введите шаг")


def ask_accuracy():
    return ask_number("Введите точность")