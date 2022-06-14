import decimal

from tabulate import tabulate

INT_MAX = 2**30
MAX_ITERATION_COUNT = 2**11


def get_answer_by_euler_method(function, a, b, y0, h, accuracy, origin, need_to_print):
    while True:
        points = [(a, y0, origin(a))]
        table = [(0, a, y0, function(a, y0), 0, origin(a))]
        count_of_iteration = int((b - a) / h)
        for i in range(1, count_of_iteration + 1):
            xi = points[i - 1][0] + h
            x_prev = points[i - 1][0]
            y_prev = points[i - 1][1]
            yi = count_yi(function, h, x_prev, y_prev, xi)
            points.append((xi, yi, origin(a)))
            e = find_accuracy(y_prev, yi)
            el = (i, xi, yi, function(xi, yi), e, origin(xi))
            table.append(el)
            if need_to_print:
                #print(tabulate([el], headers=['i', 'xi', 'yi,', 'f(xi, yi)', 'погрешность']))
                pass
        #print(tabulate(table, headers=['i', 'xi', 'yi,', 'f(xi, yi)', 'погрешность']))
        actual_accuracy = find_max_accuracy(table)
        if actual_accuracy <= accuracy:
            break
        h = h / 2
        if count_of_iteration > MAX_ITERATION_COUNT:
            print(tabulate(table, headers=['i', 'xi', 'yi,', 'f(xi, yi)', 'погрешность', 'точный ответ']))
            print(f'не удалось найти ответ за {MAX_ITERATION_COUNT} итераций')
            raise Exception(f'не удалось найти ответ за {MAX_ITERATION_COUNT} итераций')
            return

    if need_to_print:
        print(tabulate(table, headers=['i', 'xi', 'yi,', 'f(xi, yi)', 'погрешность', 'точный ответ']))
    return points


def count_yi(f, h, x_prev, y_prev, xi):
    res = y_prev + h/2 * (f(x_prev, y_prev) + f(xi, y_prev + h*f(x_prev, y_prev)))
    return res


def find_max_accuracy(table):
    accuracy = 0
    for i in range(len(table)):
        if table[i][4] > accuracy:
            accuracy = table[i][4]
    return accuracy


def find_accuracy(y0, y1):
    return abs((y0 - y1) / (2 ** 1 - 1))



def get_answer_by_adams_method(function, a, b, y0, h, accuracy, origin):
    while True:
        points = get_answer_by_euler_method(function, a, min(b, a + 3 * h), y0, h, accuracy, origin, False)
        count_of_iteration = int((b - a) / h)
        if count_of_iteration > MAX_ITERATION_COUNT:
            print(f'не удалось найти ответ за {MAX_ITERATION_COUNT} итераций')
            return
        for i in range(4, count_of_iteration + 1):
            x_prev = points[i - 1][0]
            y_prev = points[i - 1][1]
            df = get_df(function, points[i-2][0], points[i-2][1], x_prev, y_prev)
            df2 = get_df2(function, points[i - 3][0], points[i - 3][1], points[i - 2][0], points[i - 2][1], x_prev, y_prev)
            df3 = get_df3(function, points[i - 4][0], points[i - 4][1], points[i - 3][0], points[i - 3][1], points[i - 2][0], points[i - 2][1], x_prev, y_prev)
            x = x_prev + h
            y = y_prev + h * function(x_prev, y_prev) + (h ** 2)/2 * df + 5 * (h ** 3)/12 * df2 + 3 * (h ** 4)/8 * df3

            points.append((x, y, origin(x)))

        actual_accuracy = find_accuracy_adams(points)
        if actual_accuracy <= accuracy:
            break
        h = h / 2

    print(tabulate(points, headers=['xi', 'yi', 'точный ответ']))
    return points


def find_accuracy_adams(points):
    accuracy = 0
    for i in range(len(points) - 1):
        temp = abs(points[i][1] - points[i + 1][1])/(2**4 - 1)
        if temp > accuracy:
            accuracy = temp
    return accuracy


def get_df(function, x0, y0, x1, y1):
    return function(x1, y1) - function(x0, y0)


def get_df2(function, x0, y0, x1, y1, x2, y2):
    return function(x2, y2) - 2*function(x1, y1) + function(x0, y0)


def get_df3(function, x0, y0, x1, y1, x2, y2, x3, y3):
    return function(x3, y3) - 3*function(x2, y2) + 3*function(x1, y1) - function(x0, y0)


