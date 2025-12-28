package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с четырехмерными векторами
 */
public class Vector4 {
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

    /***
     * Создает вектор с заданными координатами
     * @param x
     * @param y
     * @param z
     * @param w
     */
    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Создает вектор из Vector3 с w = 1.0 (для однородных координат)
     */
    public Vector4(Vector3 v, float w) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = w;
    }

    /**
     * Создает копию вектора
     */
    public Vector4(Vector4 other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.w = other.w;
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

    public float getW() {
        return w;
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

    public void setW(float w) {
        this.w = w;
    }

    /***
     * Сложение векторов: this + other
     * @param other
     * @return
     */
    public Vector4 add(Vector4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector4(this.x + other.x, this.y + other.y, this.z + other.z, this.w + other.w);
    }

    /**
     * Вычитание векторов: this - other
     */
    public Vector4 subtract(Vector4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector4(this.x - other.x, this.y - other.y, this.z - other.z, this.w - other.w);
    }

    /**
     * Умножение на скаляр
     */
    public Vector4 multiply(float scalar) {
        return new Vector4(this.x * scalar, this.y * scalar, this.z * scalar, this.w * scalar);
    }

    /**
     * Деление на скаляр
     */
    public Vector4 divide(float scalar) {
        if (Math.abs(scalar) < 1e-7f) {
            throw new ArithmeticException("Division by zero");
        }
        return new Vector4(this.x / scalar, this.y / scalar, this.z / scalar, this.w / scalar);
    }

    /**
     * Вычисление длины вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * Вычисление квадрата длины вектора
     */
    public float lengthSquared() {
        return x * x + y * y + z * z + w * w;
    }

    /**
     * Нормализация вектора (возвращает новый нормализованный вектор)
     */
    public Vector4 normalize() {
        float len = length();
        if (len < 1e-7f) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector4(this.x / len, this.y / len, this.z / len, this.w / len);
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
        this.w /= len;
    }

    /**
     * Скалярное произведение: this · other
     */
    public float dot(Vector4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * other.x + this.y * other.y + this.z * other.z + this.w * other.w;
    }

    /**
     * Проверка на равенство с учетом погрешности
     */
    public boolean equals(Vector4 other, float epsilon) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < epsilon 
            && Math.abs(this.y - other.y) < epsilon 
            && Math.abs(this.z - other.z) < epsilon
            && Math.abs(this.w - other.w) < epsilon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector4 vector4 = (Vector4) obj;
        final float eps = 1e-7f;
        return equals(vector4, eps);
    }

    @Override
    public int hashCode() {
        return Float.hashCode(x) * 31 * 31 * 31 
            + Float.hashCode(y) * 31 * 31 
            + Float.hashCode(z) * 31 
            + Float.hashCode(w);
    }

    @Override
    public String toString() {
        return String.format("Vector4(%.3f, %.3f, %.3f, %.3f)", x, y, z, w);
    }
}
