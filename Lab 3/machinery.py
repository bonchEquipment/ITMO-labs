from tabulate import tabulate



def rectangle_method_left(function, a, b, n, need_to_log):
    return rectangle_method_template(function, a, b, n, "left", need_to_log)


def rectangle_method_center(function, a, b, n, need_to_log):
    return rectangle_method_template(function, a, b, n, "center", need_to_log)


def rectangle_method_right(function, a, b, n, need_to_log):
    return rectangle_method_template(function, a, b, n, "right", need_to_log)


def rectangle_method_template(function, a, b, n, variation, need_to_log):
    sum = 0
    length = (b - a)/n
    iterations = [[0, a, function(a)]]
    for i in range(n):
        left_border = a + length * i
        right_border = left_border + length
        point = left_border
        if variation == "center":
            point = (left_border + right_border)/2
        elif variation == "right":
            point = right_border
        sum += function(point) * length
        iterations.append([i + 1, right_border, function(right_border), point, function(point), sum])
    if need_to_log:
        print(tabulate(iterations, headers=['i', 'xi', 'yi,', 'point', 'f(point)', 'sum']), "\n")
    return sum


def trapeze_method(function, a, b, n, need_to_log):
    sum = 0
    length = (b - a) / n
    iterations = [[0, a, function(a)]]
    for i in range(n):
        left_border = a + length * i
        right_border = left_border + length
        top_base = function(left_border)
        bottom_base = function(right_border)
        sum += ((top_base + bottom_base)/2) * length
        iterations.append([i + 1, right_border, function(right_border), sum])
    if need_to_log:
        print(tabulate(iterations, headers=['i', 'xi', 'yi,', 'sum']))
    return sum


#runge
def count_accuracy(i_prev, i_current):
    return (i_prev - i_current) / 3


METHODS = [
    ("Метод левых прямоугольников", rectangle_method_left),
    ("Метод средних прямоугольников", rectangle_method_center),
    ("Метод правых прямоугольниов", rectangle_method_right),
    ("Метода трапеций", trapeze_method)
]


