package ru.vsu.cs.pronin_s_v.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Matrix4
 */
public class Matrix4Test {

    private static final float EPSILON = 1e-5f;

    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается единичная матрица 4×4 (1 на диагонали, 0 в остальных местах).
     */
    @Test
    public void testDefaultConstructor() {
        Matrix4 m = new Matrix4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, m.get(i, j), EPSILON);
            }
        }
    }


    /**
     * Тест конструктора из массива.
     * Проверяет, что матрица корректно создается из двумерного массива 4×4.
     */
    @Test
    public void testArrayConstructor() {
        float[][] arr = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        Matrix4 m = new Matrix4(arr);
        // Проверяем все элементы матрицы
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(arr[i][j], m.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, arr[i][j]));
            }
        }
    }

    /**
     * Тест обработки ошибки при создании из неверного массива.
     * Проверяет, что передача массива неправильного размера вызывает IllegalArgumentException.
     */
    @Test
    public void testInvalidArrayConstructor() {
        float[][] arr = {{1.0f, 2.0f}};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Matrix4(arr);
        });
    }

    /**
     * Тест установки единичной матрицы.
     * Проверяет, что метод setIdentity() устанавливает единичную матрицу (1 на диагонали).
     */
    @Test
    public void testSetIdentity() {
        Matrix4 m = Matrix4.zero();
        m.setIdentity();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, m.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест установки нулевой матрицы.
     * Проверяет, что метод setZero() устанавливает нулевую матрицу (все элементы = 0).
     */
    @Test
    public void testSetZero() {
        Matrix4 m = new Matrix4();
        m.setZero();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(0.0f, m.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест сложения матриц 4×4.
     * Проверяет поэлементное сложение всех элементов.
     */
    @Test
    public void testAdd() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        float[][] arr2 = {
            {10.0f, 20.0f, 30.0f, 40.0f},
            {50.0f, 60.0f, 70.0f, 80.0f},
            {90.0f, 100.0f, 110.0f, 120.0f},
            {130.0f, 140.0f, 150.0f, 160.0f}
        };
        Matrix4 m1 = new Matrix4(arr1);
        Matrix4 m2 = new Matrix4(arr2);
        Matrix4 result = m1.add(m2);
        // Проверяем все элементы результата
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = arr1[i][j] + arr2[i][j];
                Assertions.assertEquals(expected, result.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, expected));
            }
        }
    }

    /**
     * Тест обработки ошибок при операциях с null.
     * Проверяет, что передача null вызывает IllegalArgumentException для всех операций.
     */
    @Test
    public void testOperationsWithNull() {
        Matrix4 m1 = new Matrix4();
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.subtract(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.multiply((Matrix4) null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.multiply((Vector4) null));
    }

    /**
     * Тест вычитания матриц 4×4.
     * Проверяет поэлементное вычитание всех элементов.
     */
    @Test
    public void testSubtract() {
        float[][] arr1 = {
            {10.0f, 20.0f, 30.0f, 40.0f},
            {50.0f, 60.0f, 70.0f, 80.0f},
            {90.0f, 100.0f, 110.0f, 120.0f},
            {130.0f, 140.0f, 150.0f, 160.0f}
        };
        float[][] arr2 = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        Matrix4 m1 = new Matrix4(arr1);
        Matrix4 m2 = new Matrix4(arr2);
        Matrix4 result = m1.subtract(m2);
        // Проверяем все элементы результата
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = arr1[i][j] - arr2[i][j];
                Assertions.assertEquals(expected, result.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, expected));
            }
        }
    }

    /**
     * Тест умножения матриц на единичную.
     * Проверяет математическое свойство: A * I = A, где I - единичная матрица.
     * Умножение любой матрицы на единичную должно дать исходную матрицу.
     */
    @Test
    public void testMultiplyIdentity() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        Matrix4 m1 = new Matrix4(arr1);
        Matrix4 identity = new Matrix4();
        Matrix4 result = m1.multiply(identity);
        // Умножение на единичную матрицу должно дать исходную матрицу
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(arr1[i][j], result.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест умножения матриц (общий случай).
     * Проверяет корректность умножения двух произвольных матриц.
     */
    @Test
    public void testMultiply() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        float[][] arr2 = {
            {2.0f, 0.0f, 1.0f, 0.0f},
            {0.0f, 2.0f, 0.0f, 1.0f},
            {1.0f, 0.0f, 2.0f, 0.0f},
            {0.0f, 1.0f, 0.0f, 2.0f}
        };
        // Ожидаемый результат: arr1 * arr2
        // Первая строка: [1,2,3,4] * arr2 = [1*2+2*0+3*1+4*0, 1*0+2*2+3*0+4*1, 1*1+2*0+3*2+4*0, 1*0+2*1+3*0+4*2]
        // = [2+3, 0+4+4, 1+6, 2+8] = [5, 8, 7, 10]
        float[][] expected = {
            {5.0f, 8.0f, 7.0f, 10.0f},
            {17.0f, 20.0f, 19.0f, 22.0f},
            {29.0f, 32.0f, 31.0f, 34.0f},
            {41.0f, 44.0f, 43.0f, 46.0f}
        };
        Matrix4 m1 = new Matrix4(arr1);
        Matrix4 m2 = new Matrix4(arr2);
        Matrix4 result = m1.multiply(m2);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(expected[i][j], result.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, expected[i][j]));
            }
        }
    }

    /**
     * Тест умножения матрицы 4×4 на вектор-столбец Vector4.
     * Проверяет, что диагональная матрица (2,2,2,2) умножает вектор (1,2,3,4) на 2: результат (2,4,6,8).
     */
    @Test
    public void testMultiplyVector() {
        Matrix4 m = new Matrix4();
        m.set(0, 0, 2.0f);
        m.set(1, 1, 2.0f);
        m.set(2, 2, 2.0f);
        m.set(3, 3, 2.0f);
        Vector4 v = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 result = m.multiply(v);
        Assertions.assertEquals(2.0f, result.getX(), EPSILON);
        Assertions.assertEquals(4.0f, result.getY(), EPSILON);
        Assertions.assertEquals(6.0f, result.getZ(), EPSILON);
        Assertions.assertEquals(8.0f, result.getW(), EPSILON);
    }

    /**
     * Тест транспонирования матрицы 4×4.
     * Проверяет, что транспонированная матрица имеет элементы A^T[i][j] = A[j][i].
     * Транспонирование меняет строки и столбцы местами.
     */
    @Test
    public void testTranspose() {
        float[][] arr = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        Matrix4 m = new Matrix4(arr);
        Matrix4 transposed = m.transpose();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(m.get(j, i), transposed.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест вычисления определителя матрицы.
     * Проверяет определитель единичной матрицы (должен быть 1) и нулевой матрицы (должен быть 0).
     */
    @Test
    public void testDeterminant() {
        // Единичная матрица имеет определитель 1
        Matrix4 m1 = new Matrix4();
        Assertions.assertEquals(1.0f, m1.determinant(), EPSILON);
        
        // Нулевая матрица имеет определитель 0
        Matrix4 m2 = Matrix4.zero();
        Assertions.assertEquals(0.0f, m2.determinant(), EPSILON);
    }

    /**
     * Тест вычисления обратной матрицы для единичной матрицы.
     * Проверяет, что обратная матрица единичной матрицы - это сама единичная матрица.
     */
    @Test
    public void testInverseIdentity() {
        Matrix4 m = new Matrix4();
        Matrix4 inverse = m.inverse();
        // Обратная матрица единичной матрицы - это сама единичная матрица
        Assertions.assertEquals(m, inverse);
    }

    /**
     * Тест вычисления обратной матрицы (общий случай).
     * Проверяет математическое свойство: A * A^(-1) = I (единичная матрица).
     */
    @Test
    public void testInverse() {
        // Создаем матрицу с известной обратной матрицей
        // Используем диагональную матрицу [2,2,2,2], обратная = [0.5,0.5,0.5,0.5]
        Matrix4 m = new Matrix4();
        m.set(0, 0, 2.0f);
        m.set(1, 1, 2.0f);
        m.set(2, 2, 2.0f);
        m.set(3, 3, 2.0f);
        
        Matrix4 inverse = m.inverse();
        Matrix4 product = m.multiply(inverse);
        
        // Произведение должно быть единичной матрицей
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, product.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, expected));
            }
        }
    }

    /**
     * Тест обработки ошибки при вычислении обратной матрицы для вырожденной матрицы.
     * Проверяет, что попытка вычислить обратную матрицу для матрицы с определителем = 0
     * (вырожденной/сингулярной) вызывает ArithmeticException.
     */
    @Test
    public void testInverseSingular() {
        Matrix4 m = Matrix4.zero();
        Assertions.assertThrows(ArithmeticException.class, () -> {
            m.inverse();
        });
    }


    /**
     * Тест получения и установки элементов матрицы
     */
    @Test
    public void testGetSet() {
        Matrix4 m = new Matrix4();
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(0.0f, m.get(2, 3), EPSILON);
        
        m.set(2, 3, 5.0f);
        Assertions.assertEquals(5.0f, m.get(2, 3), EPSILON);
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(0.0f, m.get(0, 1), EPSILON);
    }

    /**
     * Тест обработки выхода за границы при работе с элементами матрицы.
     * Проверяет, что неверные индексы вызывают IndexOutOfBoundsException.
     */
    @Test
    public void testIndexOutOfBounds() {
        Matrix4 m = new Matrix4();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.get(4, 0), 
            "Индекс строки >= 4 должен вызывать исключение");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 4), 
            "Индекс столбца >= 4 должен вызывать исключение");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.set(-1, 0, 1.0f), 
            "Отрицательный индекс строки должен вызывать исключение");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.set(0, -1, 1.0f), 
            "Отрицательный индекс столбца должен вызывать исключение");
    }

    /**
     * Тест сравнения матриц
     */
    @Test
    public void testEquals() {
        Matrix4 m1 = new Matrix4();
        Matrix4 m2 = new Matrix4();
        Matrix4 m3 = new Matrix4();
        m3.set(0, 0, 2.0f);
        
        Assertions.assertEquals(m1, m2);
        Assertions.assertEquals(m1, m1);
        Assertions.assertNotEquals(m1, m3);
        Assertions.assertNotEquals(m1, null);
        Assertions.assertNotEquals(m1, "not a matrix");
    }
}
