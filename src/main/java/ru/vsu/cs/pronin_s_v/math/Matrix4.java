package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с матрицами 4x4
 */
public class Matrix4 {
    private static final int SIZE = 4;
    private final float[] matrix;

    /**
     * Создает единичную матрицу 4x4
     */
    public Matrix4() {
        matrix = new float[SIZE * SIZE];
        setIdentity();
    }

    /**
     * Создает нулевую матрицу 4x4
     */
    public Matrix4(boolean zero) {
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
    public Matrix4(float[][] m) {
        if (m == null || m.length != SIZE) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
        for (int i = 0; i < SIZE; i++) {
            if (m[i] == null || m[i].length != SIZE) {
                throw new IllegalArgumentException("Matrix must be 4x4");
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
    public Matrix4(float[] m) {
        if (m == null || m.length != SIZE * SIZE) {
            throw new IllegalArgumentException("Matrix must be 4x4 (16 elements)");
        }
        matrix = new float[SIZE * SIZE];
        System.arraycopy(m, 0, matrix, 0, SIZE * SIZE);
    }

    /**
     * Создает копию матрицы
     */
    public Matrix4(Matrix4 other) {
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
    public Matrix4 add(Matrix4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix4 result = new Matrix4(true);
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] + other.matrix[i];
        }
        return result;
    }

    /**
     * Вычитание матриц: this - other
     */
    public Matrix4 subtract(Matrix4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix4 result = new Matrix4(true);
        for (int i = 0; i < SIZE * SIZE; i++) {
            result.matrix[i] = this.matrix[i] - other.matrix[i];
        }
        return result;
    }

    /**
     * Умножение матриц: this * other
     */
    public Matrix4 multiply(Matrix4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix4 result = new Matrix4(true);
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
    public Vector4 multiply(Vector4 vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        float x = matrix[0 * SIZE + 0] * vector.getX() + matrix[0 * SIZE + 1] * vector.getY() 
                + matrix[0 * SIZE + 2] * vector.getZ() + matrix[0 * SIZE + 3] * vector.getW();
        float y = matrix[1 * SIZE + 0] * vector.getX() + matrix[1 * SIZE + 1] * vector.getY() 
                + matrix[1 * SIZE + 2] * vector.getZ() + matrix[1 * SIZE + 3] * vector.getW();
        float z = matrix[2 * SIZE + 0] * vector.getX() + matrix[2 * SIZE + 1] * vector.getY() 
                + matrix[2 * SIZE + 2] * vector.getZ() + matrix[2 * SIZE + 3] * vector.getW();
        float w = matrix[3 * SIZE + 0] * vector.getX() + matrix[3 * SIZE + 1] * vector.getY() 
                + matrix[3 * SIZE + 2] * vector.getZ() + matrix[3 * SIZE + 3] * vector.getW();
        return new Vector4(x, y, z, w);
    }

    /**
     * Транспонирование матрицы (возвращает новую транспонированную матрицу)
     */
    public Matrix4 transpose() {
        Matrix4 result = new Matrix4(true);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result.matrix[i * SIZE + j] = this.matrix[j * SIZE + i];
            }
        }
        return result;
    }

    /**
     * Вычисление определителя матрицы (метод разложения по первой строке)
     */
    public float determinant() {
        float det = 0.0f;
        for (int j = 0; j < SIZE; j++) {
            float sign = (j % 2 == 0) ? 1.0f : -1.0f;
            det += sign * matrix[0 * SIZE + j] * minorDeterminant(0, j);
        }
        return det;
    }

    /**
     * Вычисление минора (определителя подматрицы 3x3)
     */
    private float minorDeterminant(int row, int col) {
        float[] minor = new float[9];
        int mi = 0;
        for (int i = 0; i < SIZE; i++) {
            if (i == row) continue;
            for (int j = 0; j < SIZE; j++) {
                if (j == col) continue;
                minor[mi] = matrix[i * SIZE + j];
                mi++;
            }
        }
        // Вычисляем определитель 3x3
        float a = minor[0], b = minor[1], c = minor[2];
        float d = minor[3], e = minor[4], f = minor[5];
        float g = minor[6], h = minor[7], i = minor[8];
        return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
    }

    /**
     * Вычисление обратной матрицы
     */
    public Matrix4 inverse() {
        float det = determinant();
        if (Math.abs(det) < 1e-7f) {
            throw new ArithmeticException("Matrix is singular (determinant is zero), cannot compute inverse");
        }
        
        Matrix4 adjugate = new Matrix4(true);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                float sign = ((i + j) % 2 == 0) ? 1.0f : -1.0f;
                adjugate.matrix[j * SIZE + i] = sign * minorDeterminant(i, j) / det;
            }
        }
        return adjugate;
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
        sb.append("Matrix4:\n");
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
