from nio import *
from graphic import *
from machinery import *


def main():
    function_num = ask_function_num()
    function = get_function(function_num)
    a = ask_a()
    b = ask_b()
    y0 = ask_y0(a)
    h = ask_h()
    accuracy = ask_accuracy()
    origin = get_original_function(function_num)

    euler_answer = get_answer_by_euler_method(function, a, b, y0, h, accuracy, origin, True)
    adams_answer = get_answer_by_adams_method(function, a, b, y0, h, accuracy, origin)
    draw_graph(euler_answer, adams_answer, a, y0)


main()
