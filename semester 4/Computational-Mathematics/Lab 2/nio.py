import functions

COUNT_OF_FUNCTIONS = len(functions.list_of_functions)
COUNT_OF_FUNCTIONS_FOR_SYSTEM = len(functions.list_of_functions_for_system)
#TODO translate to english


def ask_type_of_equation():
    type_of_equation = -33
    while type_of_equation != 0 and type_of_equation != 1:
        try:
            type_of_equation = int(input("Введите 0, чтобы выбрать одно уравнение, 1, чтобы выбрать систему уравнений: "))
        except ValueError:
            pass
    return type_of_equation


def ask_function_num():
    function_num = -33
    while not is_in_range(function_num, COUNT_OF_FUNCTIONS):
        for i in range(0, COUNT_OF_FUNCTIONS):
            print(str(i) + ") " + functions.get_function_name(i))
        try:
            function_num = int(input("Введите номер желаемой функции: "))
        except ValueError:
            pass
    return function_num


def ask_interval_type():
    interval_type = -1
    while interval_type != 0 and interval_type != 1:
        try:
            interval_type = int(input("Введите 0, чтобы ввести интервал, 1, чтобы найти автоматически: "))
        except ValueError:
            pass
    return interval_type


#Ввод интервала и проверка его корректности
def ask_interval(function):
    while True:
        try:
            input_str = input("Введите границы интервала через точку с запятой (например 1;5.5): ")
            left_border = float(input_str.split(";")[0])
            right_border = float(input_str.split(";")[1])
            if left_border >= right_border:
                print("Вторая граница должна быть больше первой")
                continue
            elif function(left_border) * function(right_border) >= 0:
                print("Интервал содержит ноль или обе точки одного знака.")
                continue
            return [left_border, right_border]
        except (IndexError, TypeError, ValueError):
            print("Неверный формат ввода")


def ask_if_want_to_continue(interval):
    answer = -33
    while answer != 0 and answer != 1:
        try:
            answer = int(input(f'Найденный интервал: [{interval[0]};{interval[1]}]\n' +
                               'Введите 0, чтобы найти следующий интервал, 1, чтобы выбрать этот интервал: '))
        except ValueError:
            pass
    return answer


def ask_accuracy():
    while True:
        try:
            accuracy = float(input("Введите точность: "))
            if accuracy > 0:
                return accuracy
            print("Число должно быть положительным")
        except ValueError:
            pass


def ask_for_method():
    answer = -33
    while answer != 0 and answer != 1:
        try:
            answer = int(input('Выберите метод: 0 - метод хорд, 1 - метод простых итераций: '))
        except ValueError:
            pass
    return answer


def ask_system_function_num():
    function_num_1, function_num_2 = -33, -32
    while not is_in_range(function_num_1, COUNT_OF_FUNCTIONS_FOR_SYSTEM) or not is_in_range(function_num_2, COUNT_OF_FUNCTIONS_FOR_SYSTEM):
        for i in range(0, COUNT_OF_FUNCTIONS_FOR_SYSTEM):
            print(str(i) + ") " + functions.get_function_for_system_name(i))
        try:
            function_num_1: int = int(input("Введите номер первой функции: "))
            function_num_2 = int(input("Введите номер второй функции (другой): "))
            if function_num_1 == function_num_2:
                function_num_1, function_num_2 = -33, -32
        except ValueError:
            pass
    return function_num_1, function_num_2


def ask_start_points():
    while True:
        try:
            input_str = input("Введите начальное приближение через точку с запятой (например 1;2.3): ")
            a = float(input_str.split(";")[0])
            b = float(input_str.split(";")[1])
            break
        except (IndexError, TypeError, ValueError):
            print("Неверный формат ввода")
    return a, b


def ask_for_two_borders():
    approximation_x = float(input("Введите начальное приближение x:"))
    approximation_y = float(input("Введите начальное приближение y:"))
    return approximation_x, approximation_y


def is_in_range(number, row):
    for i in range(row):
        if i == number:
            return True
    return False
