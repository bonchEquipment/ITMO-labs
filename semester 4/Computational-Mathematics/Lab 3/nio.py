from functions import *
from machinery import *

COUNT_OF_FUNCTIONS = len(list_of_functions)


def ask_function_num():
    function_num = -33
    while not is_in_range(function_num, COUNT_OF_FUNCTIONS):
        for i in range(0, COUNT_OF_FUNCTIONS):
            print(str(i) + ") " + get_function_name(i))
        try:
            function_num = int(input("Введите номер желаемой функции: "))
        except ValueError:
            pass
    return function_num


def ask_interval():
    while True:
        try:
            input_str = input("Введите пределы интегрирования через точку с запятой (например 1;5.5): ")
            left_border = float(input_str.split(";")[0])
            right_border = float(input_str.split(";")[1])
            return [left_border, right_border]
        except (IndexError, TypeError, ValueError):
            print("Неверный формат ввода")


def ask_accuracy():
    while True:
        try:
            accuracy = float(input("Введите точность: "))
            if accuracy > 0:
                return accuracy
            print("Число должно быть положительным")
        except ValueError:
            pass


def is_in_range(number, row):
    for i in range(row):
        if i == number:
            return True
    return False


def ask_method():
    number_of_method = -1
    while not is_in_range(number_of_method, 4):
        try:
            print("Выберете метод:")
            for i in range(len(METHODS)):
                print(f'{i}) {METHODS[i][0]}')
            number_of_method = int(input())
        except (IndexError, TypeError, ValueError):
            print("Неверный формат ввода")
    return number_of_method
