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
     * Проверяет, что создается единичная матрица 3×3.
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
            new Matrix3(arr);
        });
    }

    /**
     * Тест установки единичной матрицы.
     * Проверяет, что метод setIdentity() устанавливает единичную матрицу.
     */
    @Test
    public void testSetIdentity() {
        Matrix3 m = Matrix3.zero();
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
     * Проверяет, что метод setZero() устанавливает нулевую матрицу.
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
     */
    @Test
    public void testAdd() {
        float[][] arr1 = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        float[][] arr2 = {
            {10.0f, 20.0f, 30.0f},
            {40.0f, 50.0f, 60.0f},
            {70.0f, 80.0f, 90.0f}
        };
        Matrix3 m1 = new Matrix3(arr1);
        Matrix3 m2 = new Matrix3(arr2);
        Matrix3 result = m1.add(m2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
        Matrix3 m1 = new Matrix3();
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.add(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.subtract(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.multiply((Matrix3) null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> m1.multiply((Vector3) null));
    }

    /**
     * Тест вычитания матриц.
     * Проверяет поэлементное вычитание всех элементов.
     */
    @Test
    public void testSubtract() {
        float[][] arr1 = {
            {10.0f, 20.0f, 30.0f},
            {40.0f, 50.0f, 60.0f},
            {70.0f, 80.0f, 90.0f}
        };
        float[][] arr2 = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        Matrix3 m1 = new Matrix3(arr1);
        Matrix3 m2 = new Matrix3(arr2);
        Matrix3 result = m1.subtract(m2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float expected = arr1[i][j] - arr2[i][j];
                Assertions.assertEquals(expected, result.get(i, j), EPSILON,
                    String.format("Элемент [%d][%d] должен быть равен %.1f", i, j, expected));
            }
        }
    }

    /**
     * Тест умножения матриц.
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Assertions.assertEquals(arr1[i][j], result.get(i, j), EPSILON);
            }
        }
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
     * Тест транспонирования матрицы.
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
     * Тест вычисления определителя матрицы.
     * Проверяет определитель единичной матрицы (должен быть 1) и конкретной матрицы.
     */
    @Test
    public void testDeterminant() {
        // Единичная матрица имеет определитель 1
        Matrix3 m1 = new Matrix3();
        Assertions.assertEquals(1.0f, m1.determinant(), EPSILON);
        
        // Проверяем конкретную матрицу
        float[][] arr = {
            {1.0f, 2.0f, 3.0f},
            {0.0f, 1.0f, 4.0f},
            {5.0f, 6.0f, 0.0f}
        };
        Matrix3 m2 = new Matrix3(arr);
        Assertions.assertEquals(1.0f, m2.determinant(), EPSILON);
    }

    /**
     * Тест получения и установки элементов матрицы
     */
    @Test
    public void testGetSet() {
        Matrix3 m = new Matrix3();
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(0.0f, m.get(1, 2), EPSILON);
        
        m.set(1, 2, 5.0f);
        Assertions.assertEquals(5.0f, m.get(1, 2), EPSILON);
        Assertions.assertEquals(1.0f, m.get(0, 0), EPSILON);
        Assertions.assertEquals(0.0f, m.get(0, 1), EPSILON);
    }

    /**
     * Тест обработки выхода за границы при работе с элементами матрицы.
     * Проверяет, что неверные индексы вызывают IndexOutOfBoundsException.
     */
    @Test
    public void testIndexOutOfBounds() {
        Matrix3 m = new Matrix3();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.get(3, 0), 
            "Индекс строки >= 3 должен вызывать исключение");
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> m.get(0, 3), 
            "Индекс столбца >= 3 должен вызывать исключение");
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
        Matrix3 m1 = new Matrix3();
        Matrix3 m2 = new Matrix3();
        Matrix3 m3 = new Matrix3();
        m3.set(0, 0, 2.0f);
        
        Assertions.assertTrue(m1.equals(m2));
        Assertions.assertTrue(m1.equals(m1));
        Assertions.assertFalse(m1.equals(m3));
        Assertions.assertFalse(m1.equals(null));
        Assertions.assertFalse(m1.equals("not a matrix"));
    }
}
