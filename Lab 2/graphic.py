import numpy as np
import matplotlib.pyplot as plt

import functions
from functions import *
import sympy as sp


def draw_plot(function_num, left_border=-10, right_border=10):
    min_x = -10
    min_y = -10
    max_x = 10
    max_y = 10
    step = 1

    if right_border > 10 or left_border < -10:
        min_x = left_border - 3
        min_y = left_border - 3
        max_x = right_border + 3
        max_y = right_border + 3
        step = (right_border - left_border)/10

    plot_function(get_function(function_num), get_function_name(function_num), min_x, max_x, min_y, max_y, step)


def plot_function(func, title, min_x, max_x, min_y, max_y, step):
    x = np.linspace(min_x, max_x, 10000)
    fig = plt.figure()

    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')

    ax.plot(x, func(x), "r", 2.0)
    ax.set(xlim=(min_x, max_x), xticks=np.arange(min_x, max_x, step),
           ylim=(min_y, max_y), yticks=np.arange(min_y, max_y, step))

    plt.title(title)
    plt.show()


def draw_plot_system(num1, num2):
    x, y = sp.symbols("x y")

    f1 = functions.get_function_for_system(num1)(x, y)
    f2 = functions.get_function_for_system(num2)(x, y)
    sp.plot_implicit(sp.Or(sp.Eq(f1, 0), sp.Eq(f2, 0)))
