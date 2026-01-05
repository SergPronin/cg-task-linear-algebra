package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с четырехмерными векторами
 */
public class Vector4 {
    private static final float EPSILON = 1e-7f;
    
    private float x;
    private float y;
    private float z;
    private float w;

    /**
     * Создает нулевой вектор
     */
    public Vector4() {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    /**
     * Создает вектор с заданными координатами
     * @param x координата x
     * @param y координата y
     * @param z координата z
     * @param w координата w
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    private static void requireNonNull(Vector4 vector, String paramName) {
        if (vector == null) {
            throw new IllegalArgumentException(paramName + " не может быть null");
        }
    }

    private static void requireNonNull(Vector3 vector, String paramName) {
        if (vector == null) {
            throw new IllegalArgumentException(paramName + " не может быть null");
        }
    }

    /**
     * Создает вектор из Vector3 с заданным w
     * @param v трехмерный вектор
     * @param w координата w
     */
    public Vector4(Vector3 v, float w) {
        requireNonNull(v, "Vector3");
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = w;
    }

    /**
     * Создает копию вектора
     * @param other исходный вектор
     */
    public Vector4(Vector4 other) {
        requireNonNull(other, "Vector");
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
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
     * Возвращает координату w
     * @return координата w
     */
    public float getW() {
        return w;
    }

    /**
     * Сложение векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector4 add(Vector4 other) {
        requireNonNull(other, "Vector");
        return new Vector4(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    /**
     * Вычитание векторов
     * @param other другой вектор
     * @return новый вектор
     */
    public Vector4 subtract(Vector4 other) {
        requireNonNull(other, "Vector");
        return new Vector4(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    /**
     * Умножение на скаляр
     * @param scalar скалярное значение
     * @return новый вектор
     */
    public Vector4 multiply(float scalar) {
        return new Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
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
    public Vector4 divide(float scalar) {
        checkNonZero(scalar);
        return new Vector4(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    /**
     * Вычисление длины вектора
     * @return длина вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Нормализация вектора
     * @return новый нормализованный вектор
     */
    public Vector4 normalize() {
        float len = length();
        if (len < EPSILON) {
            throw new ArithmeticException("Невозможно нормализовать нулевой вектор");
        }
        return new Vector4(this.x / len, this.y / len, this.z / len, this.w / len);
    }

    /**
     * Скалярное произведение
     * @param other другой вектор
     * @return скалярное произведение
     */
    public float dot(Vector4 other) {
        requireNonNull(other, "Vector");
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector4 vector4 = (Vector4) obj;
        return Math.abs(this.x - vector4.x) < EPSILON 
            && Math.abs(this.y - vector4.y) < EPSILON 
            && Math.abs(this.z - vector4.z) < EPSILON
            && Math.abs(this.w - vector4.w) < EPSILON;
    }

    @Override
    public int hashCode() {
        float scale = 1.0f / EPSILON;
        float maxValue = Integer.MAX_VALUE / scale;
        float safeX = Math.max(-maxValue, Math.min(maxValue, x));
        float safeY = Math.max(-maxValue, Math.min(maxValue, y));
        float safeZ = Math.max(-maxValue, Math.min(maxValue, z));
        float safeW = Math.max(-maxValue, Math.min(maxValue, w));
        return Integer.hashCode(Math.round(safeX * scale)) * 31 * 31 * 31 
             + Integer.hashCode(Math.round(safeY * scale)) * 31 * 31 
             + Integer.hashCode(Math.round(safeZ * scale)) * 31 
             + Integer.hashCode(Math.round(safeW * scale));
    }

    @Override
    public String toString() {
        return String.format("Vector4(%.3f, %.3f, %.3f, %.3f)", x, y, z, w);
    }
}
