import numpy
import math

MAX_ITERATION_COUNT = 10000
ACCURACY = 0.00001


def log(number):
    return math.log(number)


def count_deviation(points, function):
    deviations_sum = 0
    for i in range(len(points)):
        d = points[i][1] - function(points[i][0])
        deviations_sum += d*d
    mid_sqd_err = math.sqrt(deviations_sum / len(points))
    return round(mid_sqd_err, 3)


def count_s(points, function):
    s = 0
    for i in range(len(points)):
        s += (function(points[i][0]) - points[i][1])**2
    return round(s, 3)


def count_pier_correlation(points):
    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_y = sum(j for i, j in points)
    mid_x = sum_x / length
    mid_y = sum_y / length

    numerator, denominator_1, denominator_2 = 0, 0, 0
    for i in range(length):
        numerator += (points[i][0] - mid_x) * (points[i][1] - mid_y)
        denominator_2 += (points[i][1] - mid_y) ** 2
        denominator_1 += (points[i][0] - mid_x) ** 2

    try:
        correlation = numerator / (math.sqrt(denominator_1 * denominator_2))
        print(f"Коэффициент корреляции Пирсона равен: {round(correlation, 3)}")
    except Exception:
        print("Не получилось посчитать коэффициент корреляции Пирсона")


def get_points_func(function, a, b, step):
    points = []
    for i in numpy.arange(a, b+step, step):
        i = round(i, 3)
        try:
            points.append([i, round(function(i), 3)])
        except Exception:
            points.append([i+0.01, function(i+0.01)])
    return points


def solve_system_of_equations(array):
    number_of_rows = len(array)
    prev_answers = [0] * number_of_rows
    current_answers = [0] * number_of_rows
    difference = ACCURACY + 1
    number_of_iteration = 0

    while difference > ACCURACY:
        for i in range(number_of_rows):
            sum = 0
            for j in range(number_of_rows):
                if i != j:
                    sum += array[i][j] / array[i][i] * current_answers[j]
            current_answers[i] = array[i][number_of_rows] / array[i][i] - sum
        for i in current_answers:
            if i is None or i == math.inf or i == -math.inf:
                print("Значения расходятся, невозможно найти ответ")
                exit(0)

        max_difference = 0.0
        for i in range(number_of_rows):
            if abs(prev_answers[i] - current_answers[i]) > max_difference:
                max_difference = abs(prev_answers[i] - current_answers[i])
        difference = max_difference
        for i in range(number_of_rows):
            prev_answers[i] = current_answers[i]
        number_of_iteration += 1
        if number_of_iteration > MAX_ITERATION_COUNT:
            print("Не удалось получить ответ за максимальное количество итераций")
            break
    return current_answers


def get_linear_approximation(points):
    count_pier_correlation(points)

    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_y = sum(j for i, j in points)
    sum_xy = sum(i * j for i, j in points)
    sum_x_sqd = sum(i**2 for i, j in points)

    coefficients = solve_system_of_equations([[sum_x_sqd, sum_x, sum_xy], [sum_x, length, sum_y]])
    approximate_function = lambda x: coefficients[0] * x + coefficients[1]
    approximate_function_str = f'{round(coefficients[0], 3)}x + {round(coefficients[1], 3)}'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s


def get_sqr_approximation(points):
    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_x_sqd = sum(i ** 2 for i, j in points)
    sum_x_qub = sum(i ** 3 for i, j in points)
    sum_x_forth = sum(i ** 4 for i, j in points)
    sum_y = sum(j for i, j in points)
    sum_xy = sum(i * j for i, j in points)
    sum_x_sqd_y = sum((i**2) * j for i, j in points)

    system = [
        [length, sum_x, sum_x_sqd, sum_y],
        [sum_x, sum_x_sqd, sum_x_qub, sum_xy],
        [sum_x_sqd, sum_x_qub, sum_x_forth, sum_x_sqd_y]
    ]

    coefficients = solve_system_of_equations(system)
    approximate_function = lambda x: coefficients[2]*(x**2) + coefficients[1]*x + coefficients[0]
    approximate_function_str = f'{round(coefficients[2], 3)}x^2 + {round(coefficients[1], 3)}x + {round(coefficients[0], 3)}'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s


def get_qub_approximation(points):
    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_x_sqd = sum(i ** 2 for i, j in points)
    sum_x_qub = sum(i ** 3 for i, j in points)
    sum_x_forth = sum(i ** 4 for i, j in points)
    sum_x_fifth = sum(i ** 5 for i, j in points)
    sum_x_six = sum(i ** 6 for i, j in points)
    sum_y = sum(j for i, j in points)
    sum_xy = sum(i * j for i, j in points)
    sum_x_sqd_y = sum((i ** 2) * j for i, j in points)
    sum_x_cub_y = sum((i ** 3) * j for i, j in points)

    system = [
        [length, sum_x, sum_x_sqd, sum_x_qub, sum_y],
        [sum_x, sum_x_sqd, sum_x_qub, sum_x_forth, sum_xy],
        [sum_x_sqd, sum_x_qub, sum_x_forth, sum_x_fifth, sum_x_sqd_y],
        [sum_x_qub, sum_x_forth, sum_x_fifth, sum_x_six, sum_x_cub_y]
    ]

    coefficients = solve_system_of_equations(system)
    approximate_function = lambda x: coefficients[3]*(x**3) + coefficients[2]*(x**2) + coefficients[1]*x + coefficients[0]
    approximate_function_str = f'{round(coefficients[3], 3)}x^3 + {round(coefficients[2], 3)}x^2 + {round(coefficients[1], 3)}x + {round(coefficients[0], 3)}'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s


def get_degree_approximation(points):
    for i in points:
        if i[1] <= 0 or i[0] <= 0:
            return None, None, None, None

    length = len(points)
    sum_logx = sum(log(i) for i, j in points)
    sum_logx_sqd = sum(log(i)**2 for i, j in points)
    sum_logy = sum(log(j) for i, j in points)
    sum_logx_logy = sum(log(i) * log(j) for i, j in points)

    try:
        coefficients = solve_system_of_equations([[sum_logx_sqd, sum_logx, sum_logx_logy], [sum_logx, length, sum_logy]])
    except Exception:
        return None, None, None, None

    approximate_function = lambda x: numpy.exp(coefficients[1])*(x ** coefficients[0])
    approximate_function_str = f'{round(math.exp(coefficients[1]), 3)}x^{round(coefficients[0], 3)}'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s


def get_exp_approximation(points):
    for i in points:
        if i[1] <= 0:
            return None, None, None, None

    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_x_sqd = sum(i ** 2 for i, j in points)
    sum_logy = sum(log(j) for i, j in points)
    sum_x_logy = sum(i * log(j) for i, j in points)

    try:
        coefficients = solve_system_of_equations([[sum_x_sqd, sum_x, sum_x_logy], [sum_x, length, sum_logy]])
    except Exception:
        return None, None, None, None
    
    approximate_function = lambda x: numpy.exp(coefficients[1]) * numpy.exp(coefficients[0]*x)
    approximate_function_str = f'{round(math.exp(coefficients[1]), 3)}e^{round(coefficients[0], 3)}*x'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s


def get_log_approximation(points):
    for i in points:
        if i[0] <= 0:
            return None, None, None, None

    length = len(points)
    sum_x = sum(i for i, j in points)
    sum_x_sqd = sum(i ** 2 for i, j in points)
    sum_y = sum(j for i, j in points)
    sum_logx_y = sum(log(i) * j for i, j in points)

    try:
        coefficients = solve_system_of_equations([[sum_x_sqd, sum_x, sum_logx_y], [sum_x, length, sum_y]])
    except Exception:
        return None, None, None, None
    
    approximate_function = lambda x: coefficients[0] * numpy.log(x) + coefficients[1]
    approximate_function_str = f'{round(coefficients[0], 3)} ln(x) + {round(coefficients[1], 3)}'
    deviation = count_deviation(points, approximate_function)
    s = count_s(points, approximate_function)

    return approximate_function, approximate_function_str, deviation, s