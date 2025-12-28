package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с матрицами 3x3
 */
public class Matrix3 {
    private static final int SIZE = 3;
    private final float[] matrix;

    /**
     * Создает единичную матрицу 3x3
     */
    public Matrix3() {
        matrix = new float[SIZE * SIZE];
        setIdentity();
    }

    /**
     * Создает нулевую матрицу 3x3
     */
    public Matrix3(boolean zero) {
        matrix = new float[SIZE * SIZE];
        if (zero) {
            setZero();
        } else {
            setIdentity();
        }
    }

    /**
     * Создает матрицу из двумерного массива
     */
    public Matrix3(float[][] m) {
        if (m == null || m.length != SIZE) {
            throw new IllegalArgumentException("Matrix must be 3x3");
        }
        for (int i = 0; i < SIZE; i++) {
            if (m[i] == null || m[i].length != SIZE) {
                throw new IllegalArgumentException("Matrix must be 3x3");
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
     * Создает матрицу из одномерного массива (row-major order)
     */
    public Matrix3(float[] m) {
        if (m == null || m.length != SIZE * SIZE) {
            throw new IllegalArgumentException("Matrix must be 3x3 (9 elements)");
        }
        matrix = new float[SIZE * SIZE];
        System.arraycopy(m, 0, matrix, 0, SIZE * SIZE);
    }

    /**
     * Создает копию матрицы
     */
    public Matrix3(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        matrix = new float[SIZE * SIZE];
        System.arraycopy(other.matrix, 0, matrix, 0, SIZE * SIZE);
    }

    /**
     * Устанавливает единичную матрицу
     */
    public void setIdentity() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                matrix[i * SIZE + j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    /**
     * Устанавливает нулевую матрицу
     */
    public void setZero() {
        for (int i = 0; i < SIZE * SIZE; i++) {
            matrix[i] = 0.0f;
        }
    }

    /**
     * Получить значение элемента матрицы
     */
    public float get(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return matrix[row * SIZE + col];
    }

    /**
     * Установить значение элемента матрицы
     */
    public void set(int row, int col, float value) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        matrix[row * SIZE + col] = value;
    }

    /**
     * Сложение матриц: this + other
     */
    public Matrix3 add(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix3 result = new Matrix3(true);
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] + other.matrix[i];
        }
        return result;
    }

    /**
     * Вычитание матриц: this - other
     */
    public Matrix3 subtract(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix3 result = new Matrix3(true);
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] - other.matrix[i];
        }
        return result;
    }

    /**
     * Умножение матриц: this * other
     */
    public Matrix3 multiply(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix3 result = new Matrix3(true);
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
     * Умножение матрицы на вектор-столбец: this * vector
     */
    public Vector3 multiply(Vector3 vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        float x = matrix[0 * SIZE + 0] * vector.getX() + matrix[0 * SIZE + 1] * vector.getY() + matrix[0 * SIZE + 2] * vector.getZ();
        float y = matrix[1 * SIZE + 0] * vector.getX() + matrix[1 * SIZE + 1] * vector.getY() + matrix[1 * SIZE + 2] * vector.getZ();
        float z = matrix[2 * SIZE + 0] * vector.getX() + matrix[2 * SIZE + 1] * vector.getY() + matrix[2 * SIZE + 2] * vector.getZ();
        return new Vector3(x, y, z);
    }

    /**
     * Транспонирование матрицы (возвращает новую транспонированную матрицу)
     */
    public Matrix3 transpose() {
        Matrix3 result = new Matrix3(true);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.matrix[i * SIZE + j] = this.matrix[j * SIZE + i];
            }
        }
        return result;
    }

    /**
     * Вычисление определителя матрицы
     */
    public float determinant() {
        float a = matrix[0 * SIZE + 0], b = matrix[0 * SIZE + 1], c = matrix[0 * SIZE + 2];
        float d = matrix[1 * SIZE + 0], e = matrix[1 * SIZE + 1], f = matrix[1 * SIZE + 2];
        float g = matrix[2 * SIZE + 0], h = matrix[2 * SIZE + 1], i = matrix[2 * SIZE + 2];
        
        return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
    }

    /**
     * Получить копию матрицы в виде одномерного массива (row-major order)
     */
    public float[] toArray() {
        float[] result = new float[SIZE * SIZE];
        System.arraycopy(matrix, 0, result, 0, SIZE * SIZE);
        return result;
    }

    /**
     * Получить копию матрицы в виде двумерного массива
     */
    public float[][] to2DArray() {
        float[][] result = new float[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = matrix[i * SIZE + j];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix3:\n");
        for (int i = 0; i < SIZE; i++) {
            sb.append("[");
            for (int j = 0; j < SIZE; j++) {
                sb.append(String.format("%.3f", matrix[i * SIZE + j]));
                if (j < SIZE - 1) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
