package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с трехмерными векторами
 */
public class Vector3 {
    
    private float x;
    private float y;
    private float z;

    /**
     * Создает нулевой вектор
     */
    public Vector3() {
        this(0.0f, 0.0f, 0.0f);
    }

    /**
     * Создает вектор с заданными координатами
     * @param x координата x
     * @param y координата y
     * @param z координата z
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Создает копию вектора
     * @param other исходный вектор
     */
    public Vector3(Vector3 other) {
        ValidationUtils.requireNonNull(other, "Vector");
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
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

    /**
     * Возвращает координату z
     * @return координата z
     */
    public float getZ() {
        return z;
    }

    /**
     * Сложение векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector3 add(Vector3 other) {
        ValidationUtils.requireNonNull(other, "Vector");
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Вычитание векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector3 subtract(Vector3 other) {
        ValidationUtils.requireNonNull(other, "Vector");
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Умножение на скаляр
     * @param scalar скалярное значение
     * @return новый вектор
     */
    public Vector3 multiply(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Деление на скаляр
     * @param scalar скалярное значение
     * @return новый вектор
     */
    public Vector3 divide(float scalar) {
        ValidationUtils.checkNonZero(scalar);
        return new Vector3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    /**
     * Вычисление длины вектора
     * @return длина вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Нормализация вектора
     * @return новый нормализованный вектор
     */
    public Vector3 normalize() {
        float len = length();
        ValidationUtils.checkNonZeroLength(len);
        return new Vector3(this.x / len, this.y / len, this.z / len);
    }

    /**
     * Скалярное произведение
     * @param other другой вектор
     * @return скалярное произведение
     */
    public float dot(Vector3 other) {
        ValidationUtils.requireNonNull(other, "Vector");
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Векторное произведение
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector3 cross(Vector3 other) {
        ValidationUtils.requireNonNull(other, "Vector");
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3 vector3 = (Vector3) obj;
        float epsilon = ValidationUtils.getEpsilon();
        return Math.abs(this.x - vector3.x) < epsilon 
            && Math.abs(this.y - vector3.y) < epsilon 
            && Math.abs(this.z - vector3.z) < epsilon;
    }

    @Override
    public int hashCode() {
        float epsilon = ValidationUtils.getEpsilon();
        float scale = 1.0f / epsilon;
        float maxValue = Integer.MAX_VALUE / scale;
        float safeX = Math.max(-maxValue, Math.min(maxValue, x));
        float safeY = Math.max(-maxValue, Math.min(maxValue, y));
        float safeZ = Math.max(-maxValue, Math.min(maxValue, z));
        return Integer.hashCode(Math.round(safeX * scale)) * 31 * 31 
             + Integer.hashCode(Math.round(safeY * scale)) * 31 
             + Integer.hashCode(Math.round(safeZ * scale));
    }

    @Override
    public String toString() {
        return String.format("Vector3(%.3f, %.3f, %.3f)", x, y, z);
    }
}
