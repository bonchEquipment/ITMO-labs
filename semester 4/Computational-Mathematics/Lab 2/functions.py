import numpy as np

list_of_functions = [
    (lambda x: x ** 3 + x ** 2 - 4 * x - 4, "x^3 + x^2 - 4x - 4"),
    (lambda x: np.cos(x) ** 2 + np.sin(x ** 2) - x / 4, "cos(x)^2 + sin(x^2) -  x/4"),
    (lambda x: 0.15 * (x ** 2) - 0.7 * x - 0.25, "0.15x^2 - 0.7x - 0.25"),
    (lambda x: x ** 3 - 1.89 * (x ** 2) - 2 * x + 1.76, "x^3 - 1,89x^2 - 2x + 1,76")
]

list_of_functions_for_system = [
    (lambda x, y: x ** 2 + 2 * (y ** 2) - 8, "x^2 + 2y^2 - 8"),
    (lambda x, y: x ** 3 - y + 1, "x^3 - y + 3"),
    (lambda x, y: x ** 2 + y - 5, "x^2 + y - 5"),
    (lambda x, y: x + 0.1 * x ** 2 + 0.2 * y ** 2 - 0.3, "x + 0.1x^2 + 0.2y^2 - 0.3"),
    (lambda x, y: y + 0.2 * x ** 2 - 0.1 * x - 0.7, "y + 0.2x^2 - 0.1x-  0.7")
]


def get_function_for_system(num):
    return list_of_functions_for_system[num][0]


def get_function_for_system_name(num):
    return str(list_of_functions_for_system[num][1])


def get_function(num):
    return list_of_functions[num][0]


def get_function_name(num):
    return str(list_of_functions[num][1])
