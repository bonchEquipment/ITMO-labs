import matplotlib.pyplot as plt
import numpy as np


def draw_graph(points, lagrange_polynomial, newton_polynomial, argument):
    minimum_x, maximum_x, minimum_y, maximum_y = 0, 0, 0, 0
    points_x = []
    points_y = []
    interpolation_points_x = [argument, argument]
    interpolation_points_y = [lagrange_polynomial(argument), newton_polynomial(argument)]


    for i in points:
        maximum_x = max(i[0], maximum_x, argument)
        minimum_x = min(i[0], minimum_x, argument)
        maximum_y = max(i[1], maximum_y, argument, interpolation_points_y[0], interpolation_points_y[1])
        minimum_y = min(i[1], minimum_y, argument, interpolation_points_y[0], interpolation_points_y[1])
        points_x.append(i[0])
        points_y.append(i[1])

    x = np.linspace(minimum_x - 1, maximum_x + 1, 10000)

    fig = plt.figure()
    ax = fig.add_subplot(1, 1, 1)
    ax.spines['left'].set_position('center')
    ax.spines['bottom'].set_position('center')
    ax.spines['right'].set_color('none')
    ax.spines['top'].set_color('none')
    ax.xaxis.set_ticks_position('bottom')
    ax.yaxis.set_ticks_position('left')

    ax.plot(x, lagrange_polynomial(x), "r", linewidth=4.0, label="Многочлен Лагранжа")
    ax.plot(x, newton_polynomial(x), "g", linewidth=2.0, label="Многочлен Ньютона")
    ax.plot(interpolation_points_x, interpolation_points_y, linewidth=0, marker="*", markersize=10, markeredgecolor="black", markerfacecolor="red")
    ax.plot(points_x, points_y, linewidth=0, marker="*", markersize=10, markeredgecolor="black", markerfacecolor="green")
    ax.legend()
    plt.title("Интерполяции")

    ax.set(xlim=(minimum_x - 0.5, maximum_x + 0.5), xticks=np.arange(minimum_x, maximum_x, (maximum_x - minimum_x)/10),
           ylim=(minimum_y - 0.5, maximum_y + 0.5), yticks=np.arange(minimum_y, maximum_y, (maximum_y-minimum_y)/10))

    plt.show()