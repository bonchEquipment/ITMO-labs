import math

list_of_functions = [
    (lambda x, y: (x - y) ** 2, "(x - y)^2", lambda x: x),
    (lambda x, y: x**3 - 2 * y, "x^3 - 2y", lambda x: (x ^ 3) / 2),
    (lambda x, y: math.sin(x) + y, "sin(x) + y", lambda x: -math.sin(x)),
    (lambda x, y: y + (1 + x) * y*y, "y + (1 + x) * y^2", lambda x: 0),
    (lambda x, y: y + x, "y + x", lambda x: 0)
]


def get_function(num):
    return list_of_functions[num][0]


def get_function_name(num):
    return str(list_of_functions[num][1])


def get_original_function(num):
    return list_of_functions[num][2]