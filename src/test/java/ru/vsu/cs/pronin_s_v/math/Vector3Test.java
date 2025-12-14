package ru.vsu.cs.pronin_s_v.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Vector3
 */
public class Vector3Test {

    private static final float EPSILON = 1e-5f;

    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается нулевой вектор (0, 0, 0).
     */
    @Test
    public void testDefaultConstructor() {
        Vector3 v = new Vector3();
        Assertions.assertEquals(0.0f, v.getX(), EPSILON);
        Assertions.assertEquals(0.0f, v.getY(), EPSILON);
        Assertions.assertEquals(0.0f, v.getZ(), EPSILON);
    }

    /**
     * Тест конструктора с параметрами.
     * Проверяет, что вектор создается с заданными координатами (x, y, z).
     */
    @Test
    public void testConstructor() {
        Vector3 v = new Vector3(1.0f, 2.0f, 3.0f);
        Assertions.assertEquals(1.0f, v.getX(), EPSILON);
        Assertions.assertEquals(2.0f, v.getY(), EPSILON);
        Assertions.assertEquals(3.0f, v.getZ(), EPSILON);
    }

    /**
     * Тест конструктора копирования.
     * Проверяет, что создается точная копия исходного вектора.
     */
    @Test
    public void testCopyConstructor() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(v1);
        Assertions.assertEquals(v1.getX(), v2.getX(), EPSILON);
        Assertions.assertEquals(v1.getY(), v2.getY(), EPSILON);
        Assertions.assertEquals(v1.getZ(), v2.getZ(), EPSILON);
    }

    /**
     * Тест сложения трехмерных векторов.
     * Проверяет, что (1,2,3) + (4,5,6) = (5,7,9).
     */
    @Test
    public void testAdd() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 result = v1.add(v2);
        Assertions.assertEquals(5.0f, result.getX(), EPSILON);
        Assertions.assertEquals(7.0f, result.getY(), EPSILON);
        Assertions.assertEquals(9.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест вычитания трехмерных векторов.
     * Проверяет, что (5,6,7) - (2,3,4) = (3,3,3).
     */
    @Test
    public void testSubtract() {
        Vector3 v1 = new Vector3(5.0f, 6.0f, 7.0f);
        Vector3 v2 = new Vector3(2.0f, 3.0f, 4.0f);
        Vector3 result = v1.subtract(v2);
        Assertions.assertEquals(3.0f, result.getX(), EPSILON);
        Assertions.assertEquals(3.0f, result.getY(), EPSILON);
        Assertions.assertEquals(3.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест умножения трехмерного вектора на скаляр.
     * Проверяет, что (2,3,4) * 2 = (4,6,8).
     */
    @Test
    public void testMultiply() {
        Vector3 v = new Vector3(2.0f, 3.0f, 4.0f);
        Vector3 result = v.multiply(2.0f);
        Assertions.assertEquals(4.0f, result.getX(), EPSILON);
        Assertions.assertEquals(6.0f, result.getY(), EPSILON);
        Assertions.assertEquals(8.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест деления трехмерного вектора на скаляр.
     * Проверяет, что (4,6,8) / 2 = (2,3,4).
     */
    @Test
    public void testDivide() {
        Vector3 v = new Vector3(4.0f, 6.0f, 8.0f);
        Vector3 result = v.divide(2.0f);
        Assertions.assertEquals(2.0f, result.getX(), EPSILON);
        Assertions.assertEquals(3.0f, result.getY(), EPSILON);
        Assertions.assertEquals(4.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест обработки деления на ноль.
     * Проверяет, что деление на 0 вызывает ArithmeticException.
     */
    @Test
    public void testDivideByZero() {
        Vector3 v = new Vector3(4.0f, 6.0f, 8.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(0.0f);
        });
    }

    /**
     * Тест вычисления длины трехмерного вектора.
     * Проверяет, что длина (2,3,6) равна √(2²+3²+6²) = √49 = 7.
     */
    @Test
    public void testLength() {
        Vector3 v = new Vector3(2.0f, 3.0f, 6.0f);
        float expected = (float) Math.sqrt(2*2 + 3*3 + 6*6);
        Assertions.assertEquals(expected, v.length(), EPSILON);
    }

    /**
     * Тест нормализации трехмерного вектора.
     * Проверяет, что нормализованный вектор (2,0,0) имеет длину 1.0 и координаты (1,0,0).
     */
    @Test
    public void testNormalize() {
        Vector3 v = new Vector3(2.0f, 0.0f, 0.0f);
        Vector3 normalized = v.normalize();
        Assertions.assertEquals(1.0f, normalized.length(), EPSILON);
        Assertions.assertEquals(1.0f, normalized.getX(), EPSILON);
        Assertions.assertEquals(0.0f, normalized.getY(), EPSILON);
        Assertions.assertEquals(0.0f, normalized.getZ(), EPSILON);
    }

    /**
     * Тест обработки нормализации нулевого вектора.
     * Проверяет, что попытка нормализовать нулевой вектор вызывает ArithmeticException.
     */
    @Test
    public void testNormalizeZero() {
        Vector3 v = new Vector3(0.0f, 0.0f, 0.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.normalize();
        });
    }

    /**
     * Тест скалярного произведения трехмерных векторов.
     * Проверяет, что (1,2,3) · (4,5,6) = 1*4 + 2*5 + 3*6 = 32.
     */
    @Test
    public void testDot() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(4.0f, 5.0f, 6.0f);
        float result = v1.dot(v2);
        Assertions.assertEquals(32.0f, result, EPSILON); // 1*4 + 2*5 + 3*6 = 4 + 10 + 18 = 32
    }

    /**
     * Тест векторного произведения базисных векторов.
     * Проверяет, что (1,0,0) × (0,1,0) = (0,0,1).
     * Результат перпендикулярен обоим исходным векторам.
     */
    @Test
    public void testCross() {
        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 v2 = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 result = v1.cross(v2);
        Assertions.assertEquals(0.0f, result.getX(), EPSILON);
        Assertions.assertEquals(0.0f, result.getY(), EPSILON);
        Assertions.assertEquals(1.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест векторного произведения перпендикулярных векторов.
     * Проверяет, что (1,0,0) × (0,0,1) = (0,-1,0).
     */
    @Test
    public void testCrossPerpendicular() {
        Vector3 v1 = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 v2 = new Vector3(0.0f, 0.0f, 1.0f);
        Vector3 result = v1.cross(v2);
        Assertions.assertEquals(0.0f, result.getX(), EPSILON);
        Assertions.assertEquals(-1.0f, result.getY(), EPSILON);
        Assertions.assertEquals(0.0f, result.getZ(), EPSILON);
    }

    /**
     * Тест обработки ошибки при векторном произведении с null.
     * Проверяет, что передача null вызывает IllegalArgumentException.
     */
    @Test
    public void testCrossWithNull() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            v1.cross(null);
        });
    }

    /**
     * Тест антикоммутативности векторного произведения.
     * Проверяет математическое свойство: a × b = -(b × a).
     * Это важное свойство векторного произведения.
     */
    @Test
    public void testCrossAnticommutative() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(4.0f, 5.0f, 6.0f);
        Vector3 cross1 = v1.cross(v2);
        Vector3 cross2 = v2.cross(v1);
        Vector3 negCross1 = cross1.multiply(-1.0f);
        Assertions.assertTrue(cross2.equals(negCross1, EPSILON));
    }

    /**
     * Тест сравнения равных трехмерных векторов.
     * Проверяет, что два вектора с одинаковыми координатами считаются равными.
     */
    @Test
    public void testEquals() {
        Vector3 v1 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 v2 = new Vector3(1.0f, 2.0f, 3.0f);
        Assertions.assertTrue(v1.equals(v2));
    }
}
