def ask_input_source():
    mode = 0
    while mode != 1 and mode != 2:
        try:
            mode = int(input("Чтобы ввести точки нажмите 1, чтобы использовать готовую функцию 2: "))
        except ValueError:
            pass
    return mode


def ask_count_of_numbers():
    while True:
        try:
            count = int(input("Укажите колличество точек, которое собираетесь ввести: "))
            if count > 0:
                return count
        except ValueError:
            pass


def ask_for_dots(count):
    string = input("Введите точки через пробел (1,2 5,3)\n")
    array_of_dots = string.split(' ')
    numbers = []
    for i in range(count):
        temp = array_of_dots[i].split(',')
        numbers.append((float(temp[0]), float(temp[1])))
    print(f'Введенные точки: {numbers}')
    return numbers
