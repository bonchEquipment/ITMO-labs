from nio import *
from functions import *
from lagrange_machinery import *
from newton_machinery import *
from graphic import *


def main():
    if ask_input_source() == 1:
        points = ask_for_points(ask_count_of_numbers())
    else:
        points = get_points_from_function(get_function(ask_function_num()))
    print(points)
    argument = ask_for_argument()

    newton_polynomial = get_newton_polynomial(points)
    print(f'По полиному Ньютона f({argument}) = {newton_polynomial(argument)}')

    lagrange_polynomial = get_lagrange_polynomial(points)
    print(f'По полиному Лагранжа f({argument}) = {lagrange_polynomial(argument)}')

    draw_graph(points, lagrange_polynomial, newton_polynomial, argument)

    print(f'полином Лагранжа\n {get_lagrange_polynomial_str(points)}')
    print(f'полином Ньютона \n {get_newton_polynomial_str(points)}')


main()