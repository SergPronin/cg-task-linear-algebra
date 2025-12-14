package ru.vsu.cs.pronin_s_v.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Matrix3
 */
public class Matrix3Test {

    private static final float EPSILON = 1e-5f;

    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается единичная матрица 3×3 (1 на диагонали, 0 в остальных местах).
     */
    @Test
    public void testDefaultConstructor() {
        Matrix3 m = new Matrix3();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, m.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест конструктора с параметром zero.
     * Проверяет, что при передаче true создается нулевая матрица (все элементы = 0).
     */
    @Test
    public void testZeroConstructor() {
        Matrix3 m = new Matrix3(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(0.0f, m.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест конструктора из массива.
     * Проверяет, что матрица корректно создается из двумерного массива 3×3.
     */
    @Test
    public void testArrayConstructor() {
        float[][] arr = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        Matrix3 m = new Matrix3(arr);
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(5.0f, m.get(1, 1), EPSILON);
        Assertions.assertEquals(9.0f, m.get(2, 2), EPSILON);
    }

    /**
     * Тест обработки ошибки при создании из неверного массива.
     * Проверяет, что передача массива неправильного размера вызывает IllegalArgumentException.
     */
    @Test
    public void testInvalidArrayConstructor() {
        float[][] arr = {{1.0f, 2.0f}};
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Matrix3(arr);
        });
    }

    /**
     * Тест установки единичной матрицы.
     * Проверяет, что метод setIdentity() устанавливает единичную матрицу (1 на диагонали).
     */
    @Test
    public void testSetIdentity() {
        Matrix3 m = new Matrix3(true);
        m.setIdentity();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
        Matrix3 m = new Matrix3();
        m.setZero();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(0.0f, m.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест сложения матриц.
     * Проверяет поэлементное сложение: A[0][0] + B[0][0] = 1 + 2 = 3.
     */
    @Test
    public void testAdd() {
        Matrix3 m1 = new Matrix3();
        Matrix3 m2 = new Matrix3();
        m1.set(0, 0, 1.0f);
        m2.set(0, 0, 2.0f);
        Matrix3 result = m1.add(m2);
        Assertions.assertEquals(3.0f, result.get(0, 0), EPSILON);
    }

    /**
     * Тест обработки ошибки при сложении с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testAddWithNull() {
        Matrix3 m1 = new Matrix3();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m1.add(null);
        });
    }

    /**
     * Тест вычитания матриц.
     * Проверяет поэлементное вычитание: A[0][0] - B[0][0] = 5 - 2 = 3.
     */
    @Test
    public void testSubtract() {
        Matrix3 m1 = new Matrix3();
        Matrix3 m2 = new Matrix3();
        m1.set(0, 0, 5.0f);
        m2.set(0, 0, 2.0f);
        Matrix3 result = m1.subtract(m2);
        Assertions.assertEquals(3.0f, result.get(0, 0), EPSILON);
    }

    /**
     * Тест умножения матриц.
     * Проверяет математическое свойство: A * I = A, где I - единичная матрица.
     * Умножение любой матрицы на единичную должно дать исходную матрицу.
     */
    @Test
    public void testMultiply() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        float[][] arr2 = {
            {1.0f, 0.0f, 0.0f},
            {0.0f, 1.0f, 0.0f},
            {0.0f, 0.0f, 1.0f}
        };
        Matrix3 m1 = new Matrix3(arr1);
        Matrix3 m2 = new Matrix3(arr2);
        Matrix3 result = m1.multiply(m2);
        // Умножение на единичную матрицу должно дать исходную матрицу
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(arr1[i][j], result.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест обработки ошибки при умножении матриц с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testMultiplyWithNull() {
        Matrix3 m1 = new Matrix3();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m1.multiply((Matrix3) null);
        });
    }

    /**
     * Тест умножения матрицы на вектор-столбец.
     * Проверяет, что диагональная матрица (2,2,2) умножает вектор (1,2,3) на 2: результат (2,4,6).
     */
    @Test
    public void testMultiplyVector() {
        Matrix3 m = new Matrix3();
        m.set(0, 0, 2.0f);
        m.set(1, 1, 2.0f);
        m.set(2, 2, 2.0f);
        Vector3 v = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 result = m.multiply(v);
        Assertions.assertEquals(2.0f, result.getX(), EPSILON);
        Assertions.assertEquals(4.0f, result.getY(), EPSILON);
        Assertions.assertEquals(6.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест обработки ошибки при умножении матрицы на null-вектор.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testMultiplyVectorWithNull() {
        Matrix3 m = new Matrix3();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m.multiply((Vector3) null);
        });
    }

    /**
     * Тест транспонирования матрицы.
     * Проверяет, что транспонированная матрица имеет элементы A^T[i][j] = A[j][i].
     * Транспонирование меняет строки и столбцы местами.
     */
    @Test
    public void testTranspose() {
        float[][] arr = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        Matrix3 m = new Matrix3(arr);
        Matrix3 transposed = m.transpose();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(m.get(j, i), transposed.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест вычисления определителя единичной матрицы.
     * Проверяет, что определитель единичной матрицы равен 1.
     */
    @Test
    public void testDeterminant() {
        Matrix3 m = new Matrix3();
        // Единичная матрица имеет определитель 1
        Assertions.assertEquals(1.0f, m.determinant(), EPSILON);
    }

    /**
     * Тест вычисления определителя конкретной матрицы.
     * Проверяет правильность вычисления определителя по формуле разложения.
     * Для данной матрицы определитель должен быть равен 1.
     */
    @Test
    public void testDeterminantSpecific() {
        float[][] arr = {
            {1.0f, 2.0f, 3.0f},
            {0.0f, 1.0f, 4.0f},
            {5.0f, 6.0f, 0.0f}
        };
        Matrix3 m = new Matrix3(arr);
        // det = 1*(1*0 - 4*6) - 2*(0*0 - 4*5) + 3*(0*6 - 1*5)
        // = 1*(-24) - 2*(-20) + 3*(-5)
        // = -24 + 40 - 15 = 1
        Assertions.assertEquals(1.0f, m.determinant(), EPSILON);
    }

    /**
     * Тест получения и установки элементов матрицы.
     * Проверяет, что метод set() устанавливает значение, а get() его возвращает.
     */
    @Test
    public void testGetSet() {
        Matrix3 m = new Matrix3();
        m.set(1, 2, 5.0f);
        Assertions.assertEquals(5.0f, m.get(1, 2), EPSILON);
    }

    /**
     * Тест обработки выхода за границы при получении элемента.
     * Проверяет, что попытка получить элемент с индексом >= 3 вызывает IndexOutOfBoundsException.
     */
    @Test
    public void testGetOutOfBounds() {
        Matrix3 m = new Matrix3();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            m.get(3, 0);
        });
    }

    /**
     * Тест обработки выхода за границы при установке элемента.
     * Проверяет, что попытка установить элемент с отрицательным индексом вызывает IndexOutOfBoundsException.
     */
    @Test
    public void testSetOutOfBounds() {
        Matrix3 m = new Matrix3();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            m.set(-1, 0, 1.0f);
        });
    }
}
