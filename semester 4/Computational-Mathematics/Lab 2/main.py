from nio import *
from functions import *
from graphic import *
from machinery import *

methods = [lambda f, a, b, accuracy: chord_method(f, a, b, accuracy),
           lambda f, a, b, accuracy: common_iteration_method(f, a, b, accuracy)]


def play_one_equation_scenario():
    function_num = ask_function_num()
    function = get_function(function_num)
    draw_plot(function_num)

    interval = [-100, 100]

    if ask_interval_type() == 0:
        interval = ask_interval(function)
    else:
        while True:
            try:
                interval = find_interval(function, 0.2, interval[0])
                if ask_if_want_to_continue(interval) == 1:
                    break
            except IntervalNotFoundError:
                print("Не удалось найти интервал")
                interval = ask_interval(function)

    draw_plot(function_num, interval[0], interval[1])

    try:
        x, iterations_count = methods[ask_for_method()](function, interval[0], interval[1], ask_accuracy())
        print(f'корень: {x}\n'
              f'колличество итераций: {iterations_count}\n'
              f'f(x) = {function(x)}')
    except AlgorithmDivergesError:
        print("Алгоритм расходится")


def play_two_equations_scenario():
    function_num_1, function_num_2 = ask_system_function_num()
    draw_plot_system(function_num_1, function_num_2)
    approximation_x, approximation_y = ask_for_two_borders()
    accuracy = ask_accuracy()
    try:
        x, y, iterations_count = iterations_method_system(get_function_for_system(function_num_1), get_function_for_system(function_num_2), approximation_x, approximation_y, accuracy)
        print(f'корни: x = {x}; y = {y}\n'
              f'колличество итераций: {iterations_count}\n')
        f1 = get_function_for_system(function_num_1)(x, y)
        f2 = get_function_for_system(function_num_2)(x, y)
        print(f'проверка: F1(x,y):')
        print(f1)
        print(f'F2(x,y):')
        print(f2)

    except AlgorithmDivergesError:
        print("Алгоритм расходится")


if ask_type_of_equation() == 0:
    play_one_equation_scenario()
else:
    play_two_equations_scenario()
