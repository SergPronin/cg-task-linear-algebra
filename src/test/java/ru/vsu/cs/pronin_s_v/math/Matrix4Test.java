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
     * Тест конструктора с параметром zero.
     * Проверяет, что при передаче true создается нулевая матрица (все элементы = 0).
     */
    @Test
    public void testZeroConstructor() {
        Matrix4 m = new Matrix4(true);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Assertions.assertEquals(0.0f, m.get(i, j), EPSILON);
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
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(6.0f, m.get(1, 1), EPSILON);
        Assertions.assertEquals(11.0f, m.get(2, 2), EPSILON);
        Assertions.assertEquals(16.0f, m.get(3, 3), EPSILON);
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
        Matrix4 m = new Matrix4(true);
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
     * Проверяет поэлементное сложение: A[0][0] + B[0][0] = 1 + 2 = 3.
     */
    @Test
    public void testAdd() {
        Matrix4 m1 = new Matrix4();
        Matrix4 m2 = new Matrix4();
        m1.set(0, 0, 1.0f);
        m2.set(0, 0, 2.0f);
        Matrix4 result = m1.add(m2);
        Assertions.assertEquals(3.0f, result.get(0, 0), EPSILON);
    }

    /**
     * Тест обработки ошибки при сложении с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testAddWithNull() {
        Matrix4 m1 = new Matrix4();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m1.add(null);
        });
    }

    /**
     * Тест вычитания матриц 4×4.
     * Проверяет поэлементное вычитание: A[0][0] - B[0][0] = 5 - 2 = 3.
     */
    @Test
    public void testSubtract() {
        Matrix4 m1 = new Matrix4();
        Matrix4 m2 = new Matrix4();
        m1.set(0, 0, 5.0f);
        m2.set(0, 0, 2.0f);
        Matrix4 result = m1.subtract(m2);
        Assertions.assertEquals(3.0f, result.get(0, 0), EPSILON);
    }

    /**
     * Тест умножения матриц 4×4.
     * Проверяет математическое свойство: A * I = A, где I - единичная матрица.
     * Умножение любой матрицы на единичную должно дать исходную матрицу.
     */
    @Test
    public void testMultiply() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f, 4.0f},
            {5.0f, 6.0f, 7.0f, 8.0f},
            {9.0f, 10.0f, 11.0f, 12.0f},
            {13.0f, 14.0f, 15.0f, 16.0f}
        };
        Matrix4 m1 = new Matrix4(arr1);
        Matrix4 m2 = new Matrix4(); // единичная
        Matrix4 result = m1.multiply(m2);
        // Умножение на единичную матрицу должно дать исходную матрицу
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
        Matrix4 m1 = new Matrix4();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m1.multiply((Matrix4) null);
        });
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
     * Тест обработки ошибки при умножении матрицы на null-вектор.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testMultiplyVectorWithNull() {
        Matrix4 m = new Matrix4();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            m.multiply((Vector4) null);
        });
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
     * Тест вычисления определителя единичной матрицы.
     * Проверяет, что определитель единичной матрицы 4×4 равен 1.
     */
    @Test
    public void testDeterminant() {
        Matrix4 m = new Matrix4();
        // Единичная матрица имеет определитель 1
        Assertions.assertEquals(1.0f, m.determinant(), EPSILON);
    }

    /**
     * Тест вычисления определителя нулевой матрицы.
     * Проверяет, что определитель нулевой матрицы равен 0.
     */
    @Test
    public void testDeterminantZero() {
        Matrix4 m = new Matrix4(true);
        // Нулевая матрица имеет определитель 0
        Assertions.assertEquals(0.0f, m.determinant(), EPSILON);
    }

    /**
     * Тест вычисления обратной матрицы (бонусная функция).
     * Проверяет математическое свойство: A * A^(-1) = I (единичная матрица).
     * Для единичной матрицы обратная матрица - это сама единичная матрица.
     */
    @Test
    public void testInverse() {
        Matrix4 m = new Matrix4();
        Matrix4 inverse = m.inverse();
        // Обратная матрица единичной матрицы - это сама единичная матрица
        Matrix4 product = m.multiply(inverse);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, product.get(i, j), EPSILON);
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
        Matrix4 m = new Matrix4(true);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            m.inverse();
        });
    }

    /**
     * Тест вычисления обратной матрицы для конкретной диагональной матрицы.
     * Проверяет, что для диагональной матрицы обратная матрица вычисляется корректно,
     * и произведение A * A^(-1) дает единичную матрицу.
     */
    @Test
    public void testInverseSpecific() {
        // Создаем диагональную матрицу с определителем 2
        Matrix4 m = new Matrix4(true);
        m.set(0, 0, 2.0f);
        m.set(1, 1, 1.0f);
        m.set(2, 2, 1.0f);
        m.set(3, 3, 1.0f);
        Matrix4 inverse = m.inverse();
        Matrix4 product = m.multiply(inverse);
        // Проверяем, что произведение дает единичную матрицу
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float expected = (i == j) ? 1.0f : 0.0f;
                Assertions.assertEquals(expected, product.get(i, j), EPSILON);
            }
        }
    }

    /**
     * Тест получения и установки элементов матрицы 4×4.
     * Проверяет, что метод set() устанавливает значение, а get() его возвращает.
     */
    @Test
    public void testGetSet() {
        Matrix4 m = new Matrix4();
        m.set(2, 3, 5.0f);
        Assertions.assertEquals(5.0f, m.get(2, 3), EPSILON);
    }

    /**
     * Тест обработки выхода за границы при получении элемента.
     * Проверяет, что попытка получить элемент с индексом >= 4 вызывает IndexOutOfBoundsException.
     */
    @Test
    public void testGetOutOfBounds() {
        Matrix4 m = new Matrix4();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            m.get(4, 0);
        });
    }

    /**
     * Тест обработки выхода за границы при установке элемента.
     * Проверяет, что попытка установить элемент с отрицательным индексом вызывает IndexOutOfBoundsException.
     */
    @Test
    public void testSetOutOfBounds() {
        Matrix4 m = new Matrix4();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            m.set(-1, 0, 1.0f);
        });
    }
}
