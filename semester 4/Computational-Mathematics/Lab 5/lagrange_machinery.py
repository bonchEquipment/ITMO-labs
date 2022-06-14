# def get_points_from_function(function, a=-5, b=5, step=1.5):
#     points = []
#     for i in numpy.arange(a, b + step, step):
#         i = round(i, 3)
#         try:
#             points.append([i, round(function(i), 3)])
#         except Exception:
#             points.append([i + 0.01, function(i + 0.01)])
#     return points

def get_points_from_function(function):
    points = []
    for i in range(-2, 4, 2):
        points.append([i, function(i)])
    return points



def count_lagrange_polynomial(points, x_value):
    sum = 0
    for i in range(len(points)):
        sum += points[i][1] * l_function(x_value, i, points)
    return sum


def l_function(x, i, points):
    product = 1
    for j in range(len(points)):
        if j == i:
            continue
        product *= (x - points[j][0]) / (points[i][0] - points[j][0])
    return product


def get_lagrange_polynomial(points):
    return lambda x: count_lagrange_polynomial(points, x)


def get_lagrange_polynomial_str(points):
    function_str = ""
    for i in range(len(points)):
        if points[i][1] == 0:
            continue
        function_str += f'{points[i][1]} * ({l_function_str(i, points)}) + '
    function_str = function_str[:-2]
    return function_str


def l_function_str(i, points):
    l_str = ""
    for j in range(len(points)):
        if j == i:
            continue
        sign = "+" if (points[j][0] < 0) else "-"
        multiplier = -1 if(points[j][0] < 0) else 1
        l_str += f'((x {sign} {points[j][0] * multiplier})/({points[i][0] - points[j][0]}))*'
    l_str = l_str[:-1]
    return l_str