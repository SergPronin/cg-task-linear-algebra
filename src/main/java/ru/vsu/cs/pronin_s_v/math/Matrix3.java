package ru.vsu.cs.pronin_s_v.math;

import java.util.Arrays;

/**
 * Класс для работы с матрицами 3×3
 */
public class Matrix3 {
    private static final int SIZE = 3;
    
    private final float[] matrix;

    /**
     * Создает единичную матрицу
     */
    public Matrix3() {
        matrix = new float[SIZE * SIZE];
        setIdentity();
    }

    /**
     * Создает нулевую матрицу
     * @return нулевая матрица
     */
    public static Matrix3 zero() {
        Matrix3 m = new Matrix3();
        m.setZero();
        return m;
    }

    /**
     * Создает единичную матрицу
     * @return единичная матрица
     */
    public static Matrix3 identity() {
        return new Matrix3();
    }

    /**
     * Создает матрицу из двумерного массива
     * @param m двумерный массив 3×3
     */
    public Matrix3(float[][] m) {
        if (m == null || m.length != SIZE) {
            throw new IllegalArgumentException("Матрица должна быть 3x3");
        }
        for (int i = 0; i < SIZE; i++) {
            if (m[i] == null || m[i].length != SIZE) {
                throw new IllegalArgumentException("Матрица должна быть 3x3");
            }
        }
        matrix = new float[SIZE * SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i * SIZE + j] = m[i][j];
            }
        }
    }

    /**
     * Создает матрицу из одномерного массива
     * @param m одномерный массив из 9 элементов
     */
    public Matrix3(float[] m) {
        if (m == null || m.length != SIZE * SIZE) {
            throw new IllegalArgumentException("Массив должен содержать 9 элементов");
        }
        matrix = new float[SIZE * SIZE];
        System.arraycopy(m, 0, matrix, 0, SIZE * SIZE);
    }

    /**
     * Создает копию матрицы
     * @param other исходная матрица
     */
    public Matrix3(Matrix3 other) {
        ValidationUtils.requireNonNull(other, "Matrix");
        matrix = new float[SIZE * SIZE];
        System.arraycopy(other.matrix, 0, matrix, 0, SIZE * SIZE);
    }

    /**
     * Устанавливает единичную матрицу
     */
    public void setIdentity() {
        setZero();
        for (int i = 0; i < SIZE; i++) {
            matrix[i * SIZE + i] = 1.0f;
        }
    }

    /**
     * Устанавливает нулевую матрицу
     */
    public void setZero() {
        Arrays.fill(matrix, 0.0f);
    }

    private void validateIndices(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException(
                String.format("Индекс вне границ: строка=%d, столбец=%d (размер матрицы: %dx%d)", 
                    row, col, SIZE, SIZE));
        }
    }

    /**
     * Получить значение элемента матрицы
     * @param row номер строки (0-2)
     * @param col номер столбца (0-2)
     * @return значение элемента
     */
    public float get(int row, int col) {
        validateIndices(row, col);
        return matrix[row * SIZE + col];
    }

    /**
     * Установить значение элемента матрицы
     * @param row номер строки (0-2)
     * @param col номер столбца (0-2)
     * @param value новое значение
     */
    public void set(int row, int col, float value) {
        validateIndices(row, col);
        matrix[row * SIZE + col] = value;
    }


    /**
     * Сложение матриц
     * @param other другая матрица
     * @return новая матрица
     */
    public Matrix3 add(Matrix3 other) {
        ValidationUtils.requireNonNull(other, "Matrix");
        Matrix3 result = Matrix3.zero();
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] + other.matrix[i];
        }
        return result;
    }

    /**
     * Вычитание матриц
     * @param other другая матрица
     * @return новая матрица
     */
    public Matrix3 subtract(Matrix3 other) {
        ValidationUtils.requireNonNull(other, "Matrix");
        Matrix3 result = Matrix3.zero();
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] - other.matrix[i];
        }
        return result;
    }

    /**
     * Умножение матриц
     * @param other другая матрица
     * @return новая матрица
     */
    public Matrix3 multiply(Matrix3 other) {
        ValidationUtils.requireNonNull(other, "Matrix");
        Matrix3 result = Matrix3.zero();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                float sum = 0.0f;
                for (int k = 0; k < SIZE; k++) {
                    sum += this.matrix[i * SIZE + k] * other.matrix[k * SIZE + j];
                }
                result.matrix[i * SIZE + j] = sum;
            }
        }
        return result;
    }

    /**
     * Умножение матрицы на вектор
     * @param vector вектор
     * @return новый вектор
     */
    public Vector3 multiply(Vector3 vector) {
        ValidationUtils.requireNonNull(vector, "Vector");
        float[] result = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            float sum = 0.0f;
            for (int j = 0; j < SIZE; j++) {
                sum += matrix[i * SIZE + j] * getVectorComponent(vector, j);
            }
            result[i] = sum;
        }
        return new Vector3(result[0], result[1], result[2]);
    }

    private static float getVectorComponent(Vector3 vector, int index) {
        return switch (index) {
            case 0 -> vector.getX();
            case 1 -> vector.getY();
            case 2 -> vector.getZ();
            default -> throw new IllegalArgumentException("Неверный индекс компоненты вектора: " + index);
        };
    }

    /**
     * Транспонирование матрицы
     * @return новая транспонированная матрица
     */
    public Matrix3 transpose() {
        Matrix3 result = Matrix3.zero();
        for (int i = 0; i < SIZE; i++) {
            result.matrix[i * SIZE + i] = this.matrix[i * SIZE + i];
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = i + 1; j < SIZE; j++) {
                float temp = this.matrix[i * SIZE + j];
                result.matrix[i * SIZE + j] = this.matrix[j * SIZE + i];
                result.matrix[j * SIZE + i] = temp;
            }
        }
        return result;
    }

    /**
     * Вычисление определителя
     * @return определитель матрицы
     */
    public float determinant() {
        float a = matrix[0 * SIZE + 0], b = matrix[0 * SIZE + 1], c = matrix[0 * SIZE + 2];
        float d = matrix[1 * SIZE + 0], e = matrix[1 * SIZE + 1], f = matrix[1 * SIZE + 2];
        float g = matrix[2 * SIZE + 0], h = matrix[2 * SIZE + 1], i = matrix[2 * SIZE + 2];
        
        return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Matrix3 matrix3 = (Matrix3) obj;
        float epsilon = ValidationUtils.getEpsilon();
        for (int i = 0; i < SIZE * SIZE; i++) {
            if (Math.abs(this.matrix[i] - matrix3.matrix[i]) >= epsilon) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        float epsilon = ValidationUtils.getEpsilon();
        float scale = 1.0f / epsilon;
        float maxValue = Integer.MAX_VALUE / scale;
        int result = 1;
        for (int i = 0; i < SIZE * SIZE; i++) {
            float safeValue = Math.max(-maxValue, Math.min(maxValue, matrix[i]));
            result = 31 * result + Integer.hashCode(Math.round(safeValue * scale));
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Matrix3:\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("[");
            for (int j = 0; j < SIZE; j++) {
                if (j > 0) sb.append(", ");
                sb.append(String.format("%.3f", matrix[i * SIZE + j]));
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
