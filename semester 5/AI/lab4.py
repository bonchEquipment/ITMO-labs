import numpy as np
import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from sklearn.metrics import r2_score

def main():
    # Загрузка данных
    data = pd.read_csv("california_housing_train.csv")
    # Получение статистики по датасету
    print(data.describe())
    wait()

    if not data.isna().any().any():
        print("В данных нет ни одного пропущенного значения.")
    else:
        data['total_bedrooms'].fillna(data['total_bedrooms'].mean(), inplace=True)
    wait()

    # Нормировка данных
    scaler = StandardScaler()
    data_copy = data.copy()
    data_copy.iloc[:, :-1] = scaler.fit_transform(data_copy.iloc[:, :-1])

    # Делим данные на обучающий и тестовый наборы:
    X = data_copy.drop('median_house_value', axis=1)
    y = data_copy['median_house_value']

    X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

    X_train_copy = X_train
    X_test_copy = X_test

    # Обучение первой модели
    weights1, bias1 = linear_regression(X_train_copy.values, y_train.values)

    # Предсказание для первой модели
    y_pred_model1 = np.dot(X_test_copy.values, weights1) + bias1

    # Оценка производительности для первой модели
    r2_model1 = r2_score(y_test, y_pred_model1)
    print(f"R^2 Score for Model 1: {r2_model1}")
    wait()

    # Пример для второй модели (исключаем некоторые признаки)
    X_train_model2 = X_train.copy().drop(["housing_median_age", "population"], axis=1)
    X_test_model2 = X_test.copy().drop(["housing_median_age", "population"], axis=1)

    # Обучение модели 2
    weights_model2, bias_model2 = linear_regression(X_train_model2.values, y_train.values)

    # Предсказание на тестовом наборе для модели 2
    y_pred_model2 = np.dot(X_test_model2.values, weights_model2) + bias_model2

    # Оценка производительности модели 2
    r2_model2 = r2_score(y_test, y_pred_model2)
    print(f"R^2 Score for Model 2: {r2_model2}")
    wait()

    # Пример для второй модели (исключаем некоторые признаки)
    X_train_model3 = X_train.copy().drop(["total_bedrooms"], axis=1)
    X_test_model3 = X_test.copy().drop(["total_bedrooms"], axis=1)

    # Обучение модели 3
    weights_model3, bias_model3 = linear_regression(X_train_model3.values, y_train.values)

    # Предсказание на тестовом наборе для модели 3
    y_pred_model3 = np.dot(X_test_model3.values, weights_model3) + bias_model3

    # Оценка производительности модели 3
    r2_model3 = r2_score(y_test, y_pred_model3)
    print(f"R^2 Score for Model 3: {r2_model3}")
    wait()

    # Параметры для перебора
    learning_rates = [0.001, 0.01, 0.1, 0.25, 0.5, 0.7, 0.9]
    num_epochs = [500, 1000, 1500, 2000, 2500, 3000]

    print("Параметры для модели №1:")
    find_params(X_train_copy, X_test_copy, learning_rates, num_epochs, y_train, y_test)
    print("Параметры для модели №2:")
    find_params(X_train_model2, X_test_model2, learning_rates, num_epochs, y_train, y_test)
    print("Параметры для модели №3:")
    find_params(X_train_model3, X_test_model3, learning_rates, num_epochs, y_train, y_test)



def find_params(my_train_model, my_test_model, learning_rates, num_epochs, y_train, y_test):
    best_r2 = -np.inf
    best_lr = None
    best_epochs = None

    for lr in learning_rates:
        for epochs in num_epochs:
            # Обучение модели с заданными параметрами
            weights, bias = linear_regression(my_train_model.values, y_train.values, num_epochs=epochs, learning_rate=lr)

            # Предсказание на тестовом наборе
            y_pred = np.dot(my_test_model.values, weights) + bias

            # Прерывание работы вследствие переполнения
            if np.isnan(y_pred).any():
              break

            # Оценка производительности
            r2 = r2_score(y_test, y_pred)
            if r2 - best_r2 < 0.0001:
              break

            # Сохранение лучших параметров
            if r2 > best_r2:
                best_r2 = r2
                best_lr = lr
                best_epochs = epochs

    print(f"  Best R^2 Score: {best_r2}")
    print(f"  Best Learning Rate: {best_lr}")
    print(f"  Best Number of Epochs: {best_epochs}")

def linear_regression(X, y, num_epochs=2000, learning_rate=0.3):
    num_samples, num_features = X.shape
    weights = np.zeros(num_features)
    bias = 0

    for epoch in range(num_epochs):
        # Вычисляем предсказания
        y_pred = np.dot(X, weights) + bias

        # Вычисляем градиенты
        derevative_w = (1 / num_samples) * np.dot(X.T, (y_pred - y))
        derivative_b = (1 / num_samples) * np.sum(y_pred - y)

        # Обновляем веса и смещение
        weights -= learning_rate * derevative_w
        bias -= learning_rate * derivative_b

    return weights, bias

def wait():
    input("Write anything to continue:\n")


if __name__ == "__main__":
    main()
