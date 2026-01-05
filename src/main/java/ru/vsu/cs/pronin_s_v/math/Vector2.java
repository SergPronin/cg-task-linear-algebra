package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с двумерными векторами
 */
public class Vector2 {
    private static final float EPSILON = 1e-7f;
    
    private float x;
    private float y;

    /**
     * Создает нулевой вектор
     */
    public Vector2() {
        this(0.0f, 0.0f);
    }

    /**
     * Создает вектор с заданными координатами
     * @param x координата x
     * @param y координата y
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Создает копию вектора
     * @param other исходный вектор
     */
    public Vector2(Vector2 other) {
        requireNonNull(other, "Vector");
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Возвращает координату x
     * @return координата x
     */
    public float getX() {
        return x;
    }

    /**
     * Возвращает координату y
     * @return координата y
     */
    public float getY() {
        return y;
    }

    private static void requireNonNull(Vector2 vector, String paramName) {
        if (vector == null) {
            throw new IllegalArgumentException(paramName + " не может быть null");
        }
    }

    /**
     * Сложение векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector2 add(Vector2 other) {
        requireNonNull(other, "Vector");
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Вычитание векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector2 subtract(Vector2 other) {
        requireNonNull(other, "Vector");
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Умножение на скаляр
     * @param scalar скалярное значение
     * @return новый вектор
     */
    public Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    private static void checkNonZero(float scalar) {
        if (Math.abs(scalar) < EPSILON) {
            throw new ArithmeticException("Деление на ноль");
        }
    }

    /**
     * Деление на скаляр
     * @param scalar скалярное значение
     * @return новый вектор
     */
    public Vector2 divide(float scalar) {
        checkNonZero(scalar);
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    /**
     * Вычисление длины вектора
     * @return длина вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Нормализация вектора
     * @return новый нормализованный вектор
     */
    public Vector2 normalize() {
        float len = length();
        if (len < EPSILON) {
            throw new ArithmeticException("Невозможно нормализовать нулевой вектор");
        }
        return new Vector2(this.x / len, this.y / len);
    }

    /**
     * Скалярное произведение
     * @param other другой вектор
     * @return скалярное произведение
     */
    public float dot(Vector2 other) {
        requireNonNull(other, "Vector");
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Сравнение векторов
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2 vector2 = (Vector2) obj;
        return Math.abs(this.x - vector2.x) < EPSILON && Math.abs(this.y - vector2.y) < EPSILON;
    }

    @Override
    public int hashCode() {
        float scale = 1.0f / EPSILON;
        float maxValue = Integer.MAX_VALUE / scale;
        float safeX = Math.max(-maxValue, Math.min(maxValue, x));
        float safeY = Math.max(-maxValue, Math.min(maxValue, y));
        return Integer.hashCode(Math.round(safeX * scale)) * 31 
             + Integer.hashCode(Math.round(safeY * scale));
    }

    @Override
    public String toString() {
        return String.format("Vector2(%.3f, %.3f)", x, y);
    }
}
