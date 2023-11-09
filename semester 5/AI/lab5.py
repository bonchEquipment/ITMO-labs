import numpy as np
import pandas as pd
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split


def main():

    # Загрузка данных
    data = pd.read_csv("WineDataset.csv")
    print(data.describe())
    wait()

    if not data.isna().any().any():
        print("В данных нет пустых значений")
    else:
        data.dropna(inplace=True)
    wait()

    # Кодирование категориальных признаков (если они есть)
    # В данном случае нет категориальных признаков, поэтому пропускаем шаг

    scaler = StandardScaler()
    X = data.drop("Wine", axis=1)
    X = pd.DataFrame(scaler.fit_transform(X), columns=X.columns)
    y = data["Wine"]

    # Выбор случайных признаков
    np.random.seed(42)  # для воспроизводимости
    random_feature_indices = np.random.choice(X.shape[1], size=3, replace=False)
    X_model1 = X.iloc[:, random_feature_indices]

    # Разделение данных на обучающий и тестовый наборы
    X_train1, X_test1, y_train, y_test = train_test_split(X_model1, y, test_size=0.2, random_state=42)

    # Оценка на тестовом наборе для разных значений k
    k_values = [3, 5, 10]
    confusion_matrices_model1 = []

    for k in k_values:
        y_pred_model1 = [k_nearest_neighbors(X_train1.values, y_train.values, x, k) for x in X_test1.values]
        confusion_matrix = np.zeros((3, 3), dtype=int)
        for i in range(len(y_test)):
            confusion_matrix[y_test.iloc[i] - 1][y_pred_model1[i] - 1] += 1
        confusion_matrices_model1.append(confusion_matrix)

    # Вывод матрицы ошибок для модели 1
    for k, confusion_matrix in zip(k_values, confusion_matrices_model1):
        print(f"Confusion Matrix for Model 1 with k={k}:\n", confusion_matrix)

    wait()

    # Фиксированный набор признаков
    fixed_feature_indices = [0, 1, 5]  # Пример фиксированного набора признаков, перечислены индексы

    X_model2 = X.iloc[:, fixed_feature_indices]

    # Разделение данных на обучающий и тестовый наборы
    X_train2, X_test2 = train_test_split(X_model2, test_size=0.2, random_state=42)

    # Оценка на тестовом наборе для разных значений k
    confusion_matrices_model2 = []

    for k in k_values:
        y_pred_model2 = [k_nearest_neighbors(X_train2.values, y_train.values, x, k) for x in X_test2.values]
        confusion_matrix = np.zeros((3, 3), dtype=int)
        for i in range(len(y_test)):
            confusion_matrix[y_test.iloc[i] - 1][y_pred_model2[i] - 1] += 1
        confusion_matrices_model2.append(confusion_matrix)

    # Вывод матрицы ошибок для модели 2
    for k, confusion_matrix in zip(k_values, confusion_matrices_model2):
        print(f"Confusion Matrix for Model 2 with k={k}:\n", confusion_matrix)




def euclidean_distance(x1, x2):
    return np.sqrt(np.sum((x1 - x2) ** 2))

def k_nearest_neighbors(X, y, query_point, k):
    distances = [euclidean_distance(query_point, x) for x in X]
    k_indices = np.argsort(distances)[:k]
    k_nearest_labels = [y[i] for i in k_indices]
    most_common = np.bincount(k_nearest_labels).argmax()
    return most_common

def wait():
    input("Write anything to continue:\n")


if __name__ == "__main__":
    main()
