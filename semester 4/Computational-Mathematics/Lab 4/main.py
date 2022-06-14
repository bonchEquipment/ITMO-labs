from nio import *
from functions import *
from machinery import *
from tabulate import tabulate
from graphic import *


METHODS_NAMES_PAIR = [(get_linear_approximation, "Линейная"), (get_sqr_approximation, "Квадратичная"),
                      (get_qub_approximation, "Кубическая"), (get_exp_approximation, "Экспоненциальная"),
                      (get_log_approximation, "Логарифмическая"), (get_degree_approximation, "Степенная")]


def main():
    if ask_input_source() == 2:
        points = get_points_func(initial_function, -2, 0, 0.2)
    else:
        points = ask_for_dots(ask_count_of_numbers())
    print(f'Точки:  {points}')
    functions, table = [], []
    best_approximation_str = ""
    best_approximation = None
    min_deviation = math.inf

    for m in METHODS_NAMES_PAIR:
        function, function_str, deviation, s = m[0](points)
        if (deviation is not None) and deviation < min_deviation:
            best_approximation_str = m[1]
            best_approximation = function
            min_deviation = deviation
        functions.append(function)
        table.append([m[1], function_str, deviation, s])
    draw_graph(points, functions[0], functions[1], functions[2], functions[3], functions[4], functions[5])
    print(tabulate(table, headers=['Название', 'формула', 'Среднеквадратичное', 's']))
    print(f'\nЛучшая аппроксимация: {best_approximation_str}')
    draw_best_approximation(points, best_approximation, best_approximation_str)


main()