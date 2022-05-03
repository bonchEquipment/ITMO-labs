import numpy as np
from tabulate import tabulate

MAX_ITERATION_COUNT = 1000


class IntervalNotFoundError(Exception):
    pass


class AlgorithmDivergesError(Exception):
    pass


def get_derivative(function, h=0.000000001):
    return lambda x: (function(x + h) - function(x - h)) / (2 * h)


def find_interval(function, step, since):
    prev = since
    for i in np.arange(since, 1000, step):
        if function(i) * function(prev) < 0:
            return [prev, i]
        prev = i
    raise IntervalNotFoundError


def common_iteration_method(function, a, b, accuracy):
    derivative = get_derivative(function)
    max_derivative = max_of_two(derivative(a), derivative(b))

    λ = -1 / max_derivative

    φ = lambda x: x + λ * function(x)
    φ_derivative = get_derivative(φ)

    print(f'φ`(a) = {φ_derivative(a)}')
    print(f'φ`(b) = {φ_derivative(b)}')

    if abs(φ_derivative(a)) > 1 or abs(φ_derivative(b)) > 1:
        print("Не удовлетворяет достаточному условию сходимости")
    else:
        print("Удовлетворяет достаточному условию сходимости")

    x_prev = a
    x_current = φ(a)
    iterations_count = 1
    iterations = [[iterations_count, x_prev, x_current, φ(x_current), function(x_current), abs(x_current - x_prev)]]

    # Поиск корней
    while abs(x_current - x_prev) > accuracy or abs(function(x_current)) > accuracy:
        iterations_count += 1
        if iterations_count > MAX_ITERATION_COUNT:
            raise AlgorithmDivergesError("Алгоритм расходится")
        x_prev = x_current
        x_current = φ(x_prev)
        iterations.append(
            [iterations_count, x_prev, x_current, φ(x_current), function(x_current), abs(x_current - x_prev)])

    print(tabulate(iterations, headers=['№', 'xi', 'xi+1,', 'φ(xi+1)', 'f(xi+1)', '|xi+1 - xi|']))
    return x_current, iterations_count


def chord_method(function, left_border, right_border, accuracy):
    next_x_function = lambda a, b: a - ((b - a) / (function(b) - function(a))) * function(a)

    x_current = next_x_function(left_border, right_border)
    iterations_count = 0
    iterations = []

    while True:
        iterations_count += 1
        x_prev = x_current
        new_interval = choose_segment(function, left_border, x_prev, right_border)
        left_border = new_interval[0]
        right_border = new_interval[1]
        x_current = next_x_function(left_border, right_border)
        iterations.append(
            [iterations_count, left_border, right_border, x_current, function(left_border), function(right_border),
             function(x_current), abs(x_current - x_prev)])
        # if abs(x_prev - x_current) <= accuracy or abs(function(x_prev)) <= accuracy:
        if abs(x_prev - x_current) <= accuracy and abs(function(x_prev)) <= accuracy:
            break
        elif iterations_count > MAX_ITERATION_COUNT:
            raise AlgorithmDivergesError("Алгоритм расходится")

    print(tabulate(iterations, headers=['№', 'a', 'b', 'x', 'F(a)', 'F(b)', 'F(x)', '|xn+1 - xn|']))
    return x_current, iterations_count


def choose_segment(function, a, x, b):
    if function(a) * function(x) < 0:
        return [a, x]
    return [x, b]


def get_partial_derivative_x(function, x, y, h=0.00000001):
    return (function(x + h, y) - function(x - h, y)) / (2 * h)


def get_partial_derivative_y(function, x, y, h=0.00000001):
    return (function(x, y + h) - function(x, y - h)) / (2 * h)


def get_partial_derivative_f1_x(x_value):
    partial_derivative_f1_x = lambda x: 0.2*x
    return partial_derivative_f1_x(x_value)


def get_partial_derivative_f1_y(y_value):
    partial_derivative_f1_y = lambda y: 0.4*y
    return partial_derivative_f1_y(y_value)


def get_partial_derivative_f2_x(x_value):
    partial_derivative_f2_x = lambda x: 0.4*x - 0.1
    return partial_derivative_f2_x(x_value)

def get_partial_derivative_f2_y(y_value):
    partial_derivative_f2_y = lambda y: 0
    return partial_derivative_f2_y(y_value)


def iterations_method_system(function_1, function_2, approximation_x, approximation_y, accuracy):
    fi_1 = lambda x, y: x - function_1(x, y)
    fi_2 = lambda x, y: y - function_2(x, y)

    sum = max(abs(get_partial_derivative_x(fi_1, approximation_x, approximation_y)) + abs(
        get_partial_derivative_y(fi_2, approximation_x, approximation_y)),
              abs(get_partial_derivative_x(fi_2, approximation_x, approximation_y)) + abs(
                  get_partial_derivative_y(fi_1, approximation_x, approximation_y)))

    sum1 = abs(get_partial_derivative_f1_x(approximation_x)) + abs(get_partial_derivative_f2_y(approximation_y))
    sum2 = abs(get_partial_derivative_f2_x(approximation_x)) + abs(get_partial_derivative_f1_y(approximation_y))
    sum_new = max(sum1, sum2)
    print(f'sum = {sum}')
    print(f'sum1 = {sum1}, sum2 = {sum2}')

    if sum_new >= 1:
        print(sum_new)
        raise AlgorithmDivergesError("Условие сходимости не выполненно")

    last_x = approximation_x
    last_y = approximation_y
    iterations_count = 0
    iterations = []
    while True:
        iterations_count += 1
        x = fi_1(last_x, last_y)
        y = fi_2(last_x, last_y)
        iterations.append([iterations_count, last_x, abs(x - last_x), last_y, abs(y - last_y)])
        if max(abs(x - last_x), abs(y - last_y)) < accuracy:
            break
        if iterations_count > MAX_ITERATION_COUNT:
            raise AlgorithmDivergesError("Условие сходимости не выполненно")
        last_x = x
        last_y = y

    print(tabulate(iterations, headers=['№', 'xi+1', '|xi+1 - xi|', 'yi+1', '|yi+1 - yi|']))
    return x, y, iterations_count


def max_of_two(first, second):
    return first if first > second else second
