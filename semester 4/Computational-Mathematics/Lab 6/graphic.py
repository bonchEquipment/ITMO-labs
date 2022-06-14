import matplotlib.pyplot as plt


def draw_graph(euler_answer, adams_answer, x_0, y_0):
    x = [a[0] for a in euler_answer]
    y = [a[1] for a in euler_answer]
    plt.plot(x, y, label="Метод Эйлера", linewidth=5)
    x = [a[0] for a in adams_answer]
    y = [a[1] for a in adams_answer]
    plt.plot(x, y, label="Метод Адамса")
    plt.plot(x_0, y_0, marker="o", linewidth=0, label="Решение")
    plt.legend()
    plt.show()