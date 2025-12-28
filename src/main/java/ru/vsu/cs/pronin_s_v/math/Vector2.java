package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с двумерными векторами
 */
public class Vector2 {
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
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Создает копию вектора
     */
    public Vector2(Vector2 other) {
        this.x = other.x;
        this.y = other.y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    /**
     * Устанавливает все координаты вектора
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получить координаты вектора в виде массива [x, y]
     */
    public float[] toArray() {
        return new float[]{x, y};
    }

    /**
     * Сложение векторов: this + other
     */
    public Vector2 add(Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    /**
     * Вычитание векторов: this - other
     */
    public Vector2 subtract(Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    /**
     * Умножение на скаляр
     */
    public Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    /**
     * Деление на скаляр
     */
    public Vector2 divide(float scalar) {
        if (Math.abs(scalar) < 1e-7f) {
            throw new ArithmeticException("Division by zero");
        }
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    /**
     * Вычисление длины вектора
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    /**
     * Вычисление квадрата длины вектора
     */
    public float lengthSquared() {
        return x * x + y * y;
    }

    /**
     * Нормализация вектора (возвращает новый нормализованный вектор)
     */
    public Vector2 normalize() {
        float len = length();
        if (len < 1e-7f) {
            throw new ArithmeticException("Cannot normalize zero vector");
        }
        return new Vector2(this.x / len, this.y / len);
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
    }

    /**
     * Скалярное произведение: this · other
     */
    public float dot(Vector2 other) {
        if (other == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        return this.x * other.x + this.y * other.y;
    }

    /**
     * Проверка на равенство с учетом погрешности
     */
    public boolean equals(Vector2 other, float epsilon) {
        if (other == null) {
            return false;
        }
        return Math.abs(this.x - other.x) < epsilon && Math.abs(this.y - other.y) < epsilon;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vector2 vector2 = (Vector2) obj;
        final float eps = 1e-7f;
        return equals(vector2, eps);
    }

    @Override
    public int hashCode() {
        return Float.hashCode(x) * 31 + Float.hashCode(y);
    }

    @Override
    public String toString() {
        return String.format("Vector2(%.3f, %.3f)", x, y);
    }
}
