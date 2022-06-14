from nio import *
from functions import *
from machinery import *

MAX_COUNT_OF_ITERATIONS = 10


def main():
    function = get_function(ask_function_num())
    a, b = ask_interval()
    multiplier = 1
    if a > b:
        multiplier = -1
        a, b = b, a
    accuracy = ask_accuracy()
    method = METHODS[ask_method()][1]

    prev_result, current_result, n = 0, 0, 1

    for i in range(MAX_COUNT_OF_ITERATIONS):
        n *= 2
        prev_result = current_result
        current_result = method(function, a, b, n, False)
        if ((count_accuracy(prev_result, current_result)) <= accuracy) and (i > 1):
            method(function, a, b, n, True)
            print(f"Получен ответ {current_result * multiplier}, {n} разбиений")
            return
    method(function, a, b, n, True)
    print(f"Приблизительный ответ: {current_result * multiplier}, {n} разбиений")


main()