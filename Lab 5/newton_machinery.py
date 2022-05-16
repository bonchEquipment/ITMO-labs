def get_newton_polynomial(points):
    return lambda x: count_newton_polynomial(points, x)


def count_newton_polynomial(points, x):
    indexes = []
    for i in range(len(points)):
        indexes.append(i)
    sum = points[0][1]
    for k in range(1, len(points)):
        sum += f(indexes[0:k+1], points) * get_product(points, x, k)
    return sum


def f(indexes, points):
    if len(indexes) == 1:
        return points[indexes[0]][1]
    if len(indexes) == 2:
        return (points[indexes[1]][1] - points[indexes[0]][1]) / (points[indexes[1]][0] - points[indexes[0]][0])
    numerator = f(indexes[1:], points) - f([indexes[0], indexes[-2]], points)
    denominator = points[indexes[-1]][0] - points[indexes[0]][0]
    return numerator/denominator


def get_product(points, x, k):
    product = 1
    for i in range(k):
        product *= x - points[i][0]
    return product


def get_product_str(points, k):
    product = ""
    for i in range(k):
        sign = "+" if (points[i][0] < 0) else "-"
        multiplier = -1 if (points[i][0] < 0) else 1
        product += f'(x {sign} {points[i][0] * multiplier}) * '
    return product[:-2]


def get_newton_polynomial_str(points):
    indexes = []
    for i in range(len(points)):
        indexes.append(i)
    function_str = f'{points[0][1]}'
    for k in range(1, len(points)):
        func = f(indexes[0:k + 1], points)
        sign = "+" if (func < 0) else "-"
        multiplier = -1 if (func < 0) else 1
        function_str += f' {sign} {func * multiplier} * {get_product_str(points, k)}'
    return function_str