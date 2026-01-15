package ru.vsu.cs.pronin_s_v.math;

/**
 * Утилитный класс для валидации параметров
 */
final class ValidationUtils {
    private static final float EPSILON = 1e-7f;

    private ValidationUtils() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Проверяет, что объект не равен null
     * @param obj объект для проверки
     * @param paramName имя параметра для сообщения об ошибке
     * @param <T> тип объекта
     * @throws IllegalArgumentException если объект равен null
     */
    static <T> void requireNonNull(T obj, String paramName) {
        if (obj == null) {
            throw new IllegalArgumentException(paramName + " не может быть null");
        }
    }

    /**
     * Проверяет, что значение не равно нулю (с учетом погрешности)
     * @param value значение для проверки
     * @throws ArithmeticException если значение равно нулю
     */
    static void checkNonZero(float value) {
        if (Math.abs(value) < EPSILON) {
            throw new ArithmeticException("Деление на ноль");
        }
    }

    /**
     * Проверяет, что длина вектора не равна нулю
     * @param length длина вектора
     * @throws ArithmeticException если длина равна нулю
     */
    static void checkNonZeroLength(float length) {
        if (length < EPSILON) {
            throw new ArithmeticException("Невозможно нормализовать нулевой вектор");
        }
    }

    /**
     * Проверяет, что определитель матрицы не равен нулю
     * @param det определитель матрицы
     * @throws ArithmeticException если определитель равен нулю
     */
    static void checkNonZeroDeterminant(float det) {
        if (Math.abs(det) < EPSILON) {
            throw new ArithmeticException(
                "Матрица вырожденная (определитель равен нулю), невозможно вычислить обратную матрицу");
        }
    }

    /**
     * Возвращает значение EPSILON для сравнения с плавающей точкой
     * @return значение EPSILON
     */
    static float getEpsilon() {
        return EPSILON;
    }
}
