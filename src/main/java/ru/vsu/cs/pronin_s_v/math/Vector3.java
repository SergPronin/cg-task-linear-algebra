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
     */
    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Создает копию вектора
     */
    public Vector3(Vector3 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    /**
     * Сложение векторов: this + other
     */
    public Vector3 add(Vector3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    /**
     * Вычитание векторов: this - other
     */
    public Vector3 subtract(Vector3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    /**
     * Умножение на скаляр
     */
    public Vector3 multiply(float scalar) {
        return new Vector3(this.x * scalar, this.y * scalar, this.z * scalar);
    }

    /**
     * Деление на скаляр
     */
    public Vector3 divide(float scalar) {
        if (Math.abs(scalar) < 1e-7f) {
            throw new ArithmeticException("Division by zero");
        }
        return new Vector3(this.x / scalar, this.y / scalar, this.z / scalar);
    }

    /**
     * Вычисление длины вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Вычисление квадрата длины вектора
     */
    public float lengthSquared() {
        return x * x + y * y + z * z;
    }

    /**
     * Нормализация вектора (возвращает новый нормализованный вектор)
     */
    public Vector3 normalize() {
        float len = length();
        if (len < 1e-7f) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector3(this.x / len, this.y / len, this.z / len);
    }

    /**
     * Нормализация вектора на месте
     */
    public void normalizeInPlace() {
        float len = length();
        if (len < 1e-7f) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        this.x /= len;
        this.y /= len;
        this.z /= len;
    }

    /**
     * Скалярное произведение: this · other
     */
    public float dot(Vector3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    /**
     * Векторное произведение: this × other
     */
    public Vector3 cross(Vector3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector3(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }

    /**
     * Проверка на равенство с учетом погрешности
     */
    public boolean equals(Vector3 other, float epsilon) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < epsilon 
            && Math.abs(this.y - other.y) < epsilon 
            && Math.abs(this.z - other.z) < epsilon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector3 vector3 = (Vector3) obj;
        final float eps = 1e-7f;
        return equals(vector3, eps);
    }

    @Override
    public int hashCode() {
        return Float.hashCode(x) * 31 * 31 + Float.hashCode(y) * 31 + Float.hashCode(z);
    }

    @Override
    public String toString() {
        return String.format("Vector3(%.3f, %.3f, %.3f)", x, y, z);
    }
}
