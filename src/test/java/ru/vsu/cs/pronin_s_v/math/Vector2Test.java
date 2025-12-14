package ru.vsu.cs.pronin_s_v.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Vector2
 */
public class Vector2Test {

    private static final float EPSILON = 1e-5f;

    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается нулевой вектор (0, 0).
     */
    @Test
    public void testDefaultConstructor() {
        Vector2 v = new Vector2();
        Assertions.assertEquals(0.0f, v.getX(), EPSILON);
        Assertions.assertEquals(0.0f, v.getY(), EPSILON);
    }

    /**
     * Тест конструктора с параметрами.
     * Проверяет, что вектор создается с заданными координатами (x, y).
     */
    @Test
    public void testConstructor() {
        Vector2 v = new Vector2(1.0f, 2.0f);
        Assertions.assertEquals(1.0f, v.getX(), EPSILON);
        Assertions.assertEquals(2.0f, v.getY(), EPSILON);
    }

    /**
     * Тест конструктора копирования.
     * Проверяет, что создается точная копия исходного вектора.
     */
    @Test
    public void testCopyConstructor() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Vector2 v2 = new Vector2(v1);
        Assertions.assertEquals(v1.getX(), v2.getX(), EPSILON);
        Assertions.assertEquals(v1.getY(), v2.getY(), EPSILON);
    }

    /**
     * Тест сложения векторов.
     * Проверяет, что (1,2) + (3,4) = (4,6).
     */
    @Test
    public void testAdd() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Vector2 v2 = new Vector2(3.0f, 4.0f);
        Vector2 result = v1.add(v2);
        Assertions.assertEquals(4.0f, result.getX(), EPSILON);
        Assertions.assertEquals(6.0f, result.getY(), EPSILON);
    }

    /**
     * Тест обработки ошибки при сложении с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testAddWithNull() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            v1.add(null);
        });
    }

    /**
     * Тест вычитания векторов.
     * Проверяет, что (5,6) - (2,3) = (3,3).
     */
    @Test
    public void testSubtract() {
        Vector2 v1 = new Vector2(5.0f, 6.0f);
        Vector2 v2 = new Vector2(2.0f, 3.0f);
        Vector2 result = v1.subtract(v2);
        Assertions.assertEquals(3.0f, result.getX(), EPSILON);
        Assertions.assertEquals(3.0f, result.getY(), EPSILON);
    }

    /**
     * Тест обработки ошибки при вычитании с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testSubtractWithNull() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            v1.subtract(null);
        });
    }

    /**
     * Тест умножения вектора на скаляр.
     * Проверяет, что (2,3) * 2 = (4,6).
     */
    @Test
    public void testMultiply() {
        Vector2 v = new Vector2(2.0f, 3.0f);
        Vector2 result = v.multiply(2.0f);
        Assertions.assertEquals(4.0f, result.getX(), EPSILON);
        Assertions.assertEquals(6.0f, result.getY(), EPSILON);
    }

    /**
     * Тест умножения вектора на ноль.
     * Проверяет, что умножение на 0 дает нулевой вектор (0,0).
     */
    @Test
    public void testMultiplyByZero() {
        Vector2 v = new Vector2(2.0f, 3.0f);
        Vector2 result = v.multiply(0.0f);
        Assertions.assertEquals(0.0f, result.getX(), EPSILON);
        Assertions.assertEquals(0.0f, result.getY(), EPSILON);
    }

    /**
     * Тест деления вектора на скаляр.
     * Проверяет, что (4,6) / 2 = (2,3).
     */
    @Test
    public void testDivide() {
        Vector2 v = new Vector2(4.0f, 6.0f);
        Vector2 result = v.divide(2.0f);
        Assertions.assertEquals(2.0f, result.getX(), EPSILON);
        Assertions.assertEquals(3.0f, result.getY(), EPSILON);
    }

    /**
     * Тест обработки деления на ноль.
     * Проверяет, что деление на 0 вызывает ArithmeticException.
     */
    @Test
    public void testDivideByZero() {
        Vector2 v = new Vector2(4.0f, 6.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(0.0f);
        });
    }

    /**
     * Тест вычисления длины вектора.
     * Проверяет, что длина вектора (3,4) равна 5.0 (теорема Пифагора: √(3²+4²) = 5).
     */
    @Test
    public void testLength() {
        Vector2 v = new Vector2(3.0f, 4.0f);
        Assertions.assertEquals(5.0f, v.length(), EPSILON);
    }

    /**
     * Тест длины нулевого вектора.
     * Проверяет, что длина нулевого вектора (0,0) равна 0.0.
     */
    @Test
    public void testLengthZero() {
        Vector2 v = new Vector2(0.0f, 0.0f);
        Assertions.assertEquals(0.0f, v.length(), EPSILON);
    }

    /**
     * Тест вычисления квадрата длины вектора.
     * Проверяет, что квадрат длины (3,4) равен 25.0 (3²+4² = 9+16 = 25).
     * Это оптимизация, когда не нужна точная длина.
     */
    @Test
    public void testLengthSquared() {
        Vector2 v = new Vector2(3.0f, 4.0f);
        Assertions.assertEquals(25.0f, v.lengthSquared(), EPSILON);
    }

    /**
     * Тест нормализации вектора.
     * Проверяет, что нормализованный вектор (3,4) имеет длину 1.0 и координаты (0.6, 0.8).
     * Нормализация: v / |v| = (3/5, 4/5) = (0.6, 0.8).
     */
    @Test
    public void testNormalize() {
        Vector2 v = new Vector2(3.0f, 4.0f);
        Vector2 normalized = v.normalize();
        Assertions.assertEquals(1.0f, normalized.length(), EPSILON);
        Assertions.assertEquals(0.6f, normalized.getX(), EPSILON);
        Assertions.assertEquals(0.8f, normalized.getY(), EPSILON);
    }

    /**
     * Тест обработки нормализации нулевого вектора.
     * Проверяет, что попытка нормализовать нулевой вектор вызывает ArithmeticException.
     */
    @Test
    public void testNormalizeZero() {
        Vector2 v = new Vector2(0.0f, 0.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.normalize();
        });
    }

    /**
     * Тест нормализации вектора на месте (in-place).
     * Проверяет, что метод normalizeInPlace() изменяет сам вектор, а не создает новый.
     */
    @Test
    public void testNormalizeInPlace() {
        Vector2 v = new Vector2(3.0f, 4.0f);
        v.normalizeInPlace();
        Assertions.assertEquals(1.0f, v.length(), EPSILON);
    }

    /**
     * Тест скалярного произведения векторов.
     * Проверяет, что (1,2) · (3,4) = 1*3 + 2*4 = 11.
     */
    @Test
    public void testDot() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Vector2 v2 = new Vector2(3.0f, 4.0f);
        float result = v1.dot(v2);
        Assertions.assertEquals(11.0f, result, EPSILON);
    }

    /**
     * Тест обработки ошибки при скалярном произведении с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testDotWithNull() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            v1.dot(null);
        });
    }

    /**
     * Тест скалярного произведения перпендикулярных векторов.
     * Проверяет, что скалярное произведение перпендикулярных векторов равно 0.
     * Векторы (1,0) и (0,1) перпендикулярны, поэтому их скалярное произведение = 0.
     */
    @Test
    public void testDotPerpendicular() {
        Vector2 v1 = new Vector2(1.0f, 0.0f);
        Vector2 v2 = new Vector2(0.0f, 1.0f);
        float result = v1.dot(v2);
        Assertions.assertEquals(0.0f, result, EPSILON);
    }

    /**
     * Тест сравнения равных векторов.
     * Проверяет, что два вектора с одинаковыми координатами считаются равными.
     */
    @Test
    public void testEquals() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Vector2 v2 = new Vector2(1.0f, 2.0f);
        Assertions.assertTrue(v1.equals(v2));
    }

    /**
     * Тест сравнения неравных векторов.
     * Проверяет, что векторы с разными координатами считаются неравными.
     */
    @Test
    public void testNotEquals() {
        Vector2 v1 = new Vector2(1.0f, 2.0f);
        Vector2 v2 = new Vector2(1.0f, 3.0f);
        Assertions.assertFalse(v1.equals(v2));
    }

    /**
     * Тест строкового представления вектора.
     * Проверяет, что метод toString() возвращает строку, содержащую "Vector2".
     */
    @Test
    public void testToString() {
        Vector2 v = new Vector2(1.5f, 2.5f);
        String str = v.toString();
        Assertions.assertTrue(str.contains("Vector2"));
    }
}
