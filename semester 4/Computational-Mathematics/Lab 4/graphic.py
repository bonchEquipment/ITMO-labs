import matplotlib.pyplot as plt
import numpy as np


def draw_graph(points, linear_function, sqd_function, qub_function, exp_function, log_function, deg_function):
    minimum_x, maximum_x, minimum_y, maximum_y = 0, 0, 0, 0
    points_x = []
    points_y = []

    for i in points:
        maximum_x = max(i[0], maximum_x)
        minimum_x = min(i[0], minimum_x)
        maximum_y = max(i[1], maximum_y)
        minimum_y = min(i[1], minimum_y)
        points_x.append(i[0])
        points_y.append(i[1])

    x = np.linspace(minimum_x - 0.5, maximum_x + 0.5, 10000)

    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')

    ax.plot(x, linear_function(x), "r", linewidth=2.0, label="Линейная")
    ax.plot(x, sqd_function(x), "g", linewidth=2.0, label="Квадратичная")
    ax.plot(x, qub_function(x), "b", linewidth=2.0, label="Кубическая")
    if exp_function is not None:
        ax.plot(x, exp_function(x), "pink", linewidth=2.0, label="Экспоненциальная")
    x = np.linspace(0.000001, maximum_x + 0.5, 10000)
    if log_function is not None:
        ax.plot(x, log_function(x), "darkred", linewidth=2.0, label="Логарифмическая")
    if deg_function is not None:
        ax.plot(x, deg_function(x), "purple", linewidth=2.0, label="Степенная")
    ax.legend()
    ax.plot(points_x, points_y, linewidth=0, marker="*", markersize=10, markeredgecolor="black", markerfacecolor="green")

    ax.set(xlim=(minimum_x - 0.5, maximum_x + 0.5), xticks=np.arange(minimum_x, maximum_x, 0.5),
           ylim=(minimum_y - 0.5, maximum_y + 0.5), yticks=np.arange(minimum_y, maximum_y, 0.5))

    plt.show()


def draw_best_approximation(points, function, name):
    minimum_x, maximum_x, minimum_y, maximum_y = 0, 0, 0, 0
    points_x = []
    points_y = []

    for i in points:
        maximum_x = max(i[0], maximum_x)
        minimum_x = min(i[0], minimum_x)
        maximum_y = max(i[1], maximum_y)
        minimum_y = min(i[1], minimum_y)
        points_x.append(i[0])
        points_y.append(i[1])

    x = np.linspace(minimum_x - 0.5, maximum_x + 0.5, 10000)
    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')
    ax.plot(x, function(x), "b", linewidth=2.0, label=name)
    ax.plot(points_x, points_y, linewidth=0, marker="*", markersize=10, markeredgecolor="black",
            markerfacecolor="green")
    ax.set(xlim=(minimum_x - 0.5, maximum_x + 0.5), xticks=np.arange(minimum_x, maximum_x, 0.5),
           ylim=(minimum_y - 0.5, maximum_y + 0.5), yticks=np.arange(minimum_y, maximum_y, 0.5))
    ax.legend()
    plt.title("Лучшая аппроксимация")
    plt.show()
