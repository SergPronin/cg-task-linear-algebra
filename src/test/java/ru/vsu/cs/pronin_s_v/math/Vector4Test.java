package ru.vsu.cs.pronin_s_v.math;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Vector4
 */
public class Vector4Test {

    private static final float EPSILON = 1e-5f;

    /**
     * Тест конструктора по умолчанию.
     * Проверяет, что создается нулевой вектор (0, 0, 0, 0).
     */
    @Test
    public void testDefaultConstructor() {
        Vector4 v = new Vector4();
        Assertions.assertEquals(0.0f, v.getX(), EPSILON);
        Assertions.assertEquals(0.0f, v.getY(), EPSILON);
        Assertions.assertEquals(0.0f, v.getZ(), EPSILON);
        Assertions.assertEquals(0.0f, v.getW(), EPSILON);
    }

    /**
     * Тест конструктора с параметрами.
     * Проверяет, что вектор создается с заданными координатами (x, y, z, w).
     */
    @Test
    public void testConstructor() {
        Vector4 v = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Assertions.assertEquals(1.0f, v.getX(), EPSILON);
        Assertions.assertEquals(2.0f, v.getY(), EPSILON);
        Assertions.assertEquals(3.0f, v.getZ(), EPSILON);
        Assertions.assertEquals(4.0f, v.getW(), EPSILON);
    }

    /**
     * Тест конструктора из Vector3.
     * Проверяет создание Vector4 из Vector3 с заданным w (важно для однородных координат).
     * Используется в 3D-графике для преобразований.
     */
    @Test
    public void testConstructorFromVector3() {
        Vector3 v3 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector4 v = new Vector4(v3, 1.0f);
        Assertions.assertEquals(1.0f, v.getX(), EPSILON);
        Assertions.assertEquals(2.0f, v.getY(), EPSILON);
        Assertions.assertEquals(3.0f, v.getZ(), EPSILON);
        Assertions.assertEquals(1.0f, v.getW(), EPSILON);
    }

    /**
     * Тест конструктора копирования.
     * Проверяет, что создается точная копия исходного вектора.
     */
    @Test
    public void testCopyConstructor() {
        Vector4 v1 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 v2 = new Vector4(v1);
        Assertions.assertEquals(v1.getX(), v2.getX(), EPSILON);
        Assertions.assertEquals(v1.getY(), v2.getY(), EPSILON);
        Assertions.assertEquals(v1.getZ(), v2.getZ(), EPSILON);
        Assertions.assertEquals(v1.getW(), v2.getW(), EPSILON);
    }

    /**
     * Тест сложения четырехмерных векторов.
     * Проверяет, что (1,2,3,4) + (5,6,7,8) = (6,8,10,12).
     */
    @Test
    public void testAdd() {
        Vector4 v1 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 v2 = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 result = v1.add(v2);
        Assertions.assertEquals(6.0f, result.getX(), EPSILON);
        Assertions.assertEquals(8.0f, result.getY(), EPSILON);
        Assertions.assertEquals(10.0f, result.getZ(), EPSILON);
        Assertions.assertEquals(12.0f, result.getW(), EPSILON);
    }

    /**
     * Тест вычитания четырехмерных векторов.
     * Проверяет, что (5,6,7,8) - (1,2,3,4) = (4,4,4,4).
     */
    @Test
    public void testSubtract() {
        Vector4 v1 = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        Vector4 v2 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 result = v1.subtract(v2);
        Assertions.assertEquals(4.0f, result.getX(), EPSILON);
        Assertions.assertEquals(4.0f, result.getY(), EPSILON);
        Assertions.assertEquals(4.0f, result.getZ(), EPSILON);
        Assertions.assertEquals(4.0f, result.getW(), EPSILON);
    }

    /**
     * Тест умножения четырехмерного вектора на скаляр.
     * Проверяет, что (2,3,4,5) * 2 = (4,6,8,10).
     */
    @Test
    public void testMultiply() {
        Vector4 v = new Vector4(2.0f, 3.0f, 4.0f, 5.0f);
        Vector4 result = v.multiply(2.0f);
        Assertions.assertEquals(4.0f, result.getX(), EPSILON);
        Assertions.assertEquals(6.0f, result.getY(), EPSILON);
        Assertions.assertEquals(8.0f, result.getZ(), EPSILON);
        Assertions.assertEquals(10.0f, result.getW(), EPSILON);
    }

    /**
     * Тест деления четырехмерного вектора на скаляр.
     * Проверяет, что (4,6,8,10) / 2 = (2,3,4,5).
     */
    @Test
    public void testDivide() {
        Vector4 v = new Vector4(4.0f, 6.0f, 8.0f, 10.0f);
        Vector4 result = v.divide(2.0f);
        Assertions.assertEquals(2.0f, result.getX(), EPSILON);
        Assertions.assertEquals(3.0f, result.getY(), EPSILON);
        Assertions.assertEquals(4.0f, result.getZ(), EPSILON);
        Assertions.assertEquals(5.0f, result.getW(), EPSILON);
    }

    /**
     * Тест обработки деления на ноль.
     * Проверяет, что деление на 0 вызывает ArithmeticException.
     */
    @Test
    public void testDivideByZero() {
        Vector4 v = new Vector4(4.0f, 6.0f, 8.0f, 10.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.divide(0.0f);
        });
    }

    /**
     * Тест вычисления длины четырехмерного вектора.
     * Проверяет, что длина (2,3,6,0) равна √(2²+3²+6²) = √49 = 7.
     * Компонента w=0 не влияет на длину в данном случае.
     */
    @Test
    public void testLength() {
        Vector4 v = new Vector4(2.0f, 3.0f, 6.0f, 0.0f);
        float expected = (float) Math.sqrt(2*2 + 3*3 + 6*6);
        Assertions.assertEquals(expected, v.length(), EPSILON);
    }

    /**
     * Тест нормализации четырехмерного вектора.
     * Проверяет, что нормализованный вектор (2,0,0,0) имеет длину 1.0 и координаты (1,0,0,0).
     */
    @Test
    public void testNormalize() {
        Vector4 v = new Vector4(2.0f, 0.0f, 0.0f, 0.0f);
        Vector4 normalized = v.normalize();
        Assertions.assertEquals(1.0f, normalized.length(), EPSILON);
        Assertions.assertEquals(1.0f, normalized.getX(), EPSILON);
    }

    /**
     * Тест обработки нормализации нулевого вектора.
     * Проверяет, что попытка нормализовать нулевой вектор вызывает ArithmeticException.
     */
    @Test
    public void testNormalizeZero() {
        Vector4 v = new Vector4(0.0f, 0.0f, 0.0f, 0.0f);
        Assertions.assertThrows(ArithmeticException.class, () -> {
            v.normalize();
        });
    }

    /**
     * Тест скалярного произведения четырехмерных векторов.
     * Проверяет, что (1,2,3,4) · (5,6,7,8) = 1*5 + 2*6 + 3*7 + 4*8 = 70.
     */
    @Test
    public void testDot() {
        Vector4 v1 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 v2 = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        float result = v1.dot(v2);
        Assertions.assertEquals(70.0f, result, EPSILON); // 1*5 + 2*6 + 3*7 + 4*8 = 5 + 12 + 21 + 32 = 70
    }

    /**
     * Тест сравнения равных четырехмерных векторов.
     * Проверяет, что два вектора с одинаковыми координатами считаются равными.
     */
    @Test
    public void testEquals() {
        Vector4 v1 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 v2 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Assertions.assertTrue(v1.equals(v2));
    }
}
