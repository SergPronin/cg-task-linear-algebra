package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с матрицами 3x3
 */
public class Matrix3 {
    private final float[][] matrix;

    /**
     * Создает единичную матрицу 3x3
     */
    public Matrix3() {
        matrix = new float[3][3];
        setIdentity();
    }

    /**
     * Создает нулевую матрицу 3x3
     */
    public Matrix3(boolean zero) {
        matrix = new float[3][3];
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
        if (m == null || m.length != 3) {
            throw new IllegalArgumentException("Matrix must be 3x3");
        }
        for (int i = 0; i < 3; i++) {
            if (m[i] == null || m[i].length != 3) {
                throw new IllegalArgumentException("Matrix must be 3x3");
            }
        }
        matrix = new float[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(m[i], 0, matrix[i], 0, 3);
        }
    }

    /**
     * Создает копию матрицы
     */
    public Matrix3(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        matrix = new float[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(other.matrix[i], 0, matrix[i], 0, 3);
        }
    }

    /**
     * Устанавливает единичную матрицу
     */
    public void setIdentity() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    /**
     * Устанавливает нулевую матрицу
     */
    public void setZero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = 0.0f;
            }
        }
    }

    /**
     * Получить значение элемента матрицы
     */
    public float get(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return matrix[row][col];
    }

    /**
     * Установить значение элемента матрицы
     */
    public void set(int row, int col, float value) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        matrix[row][col] = value;
    }

    /**
     * Сложение матриц: this + other
     */
    public Matrix3 add(Matrix3 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix3 result = new Matrix3(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.matrix[i][j] = this.matrix[i][j] - other.matrix[i][j];
            }
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
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 3; k++) {
                    sum += this.matrix[i][k] * other.matrix[k][j];
                }
                result.matrix[i][j] = sum;
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
        float x = matrix[0][0] * vector.getX() + matrix[0][1] * vector.getY() + matrix[0][2] * vector.getZ();
        float y = matrix[1][0] * vector.getX() + matrix[1][1] * vector.getY() + matrix[1][2] * vector.getZ();
        float z = matrix[2][0] * vector.getX() + matrix[2][1] * vector.getY() + matrix[2][2] * vector.getZ();
        return new Vector3(x, y, z);
    }

    /**
     * Транспонирование матрицы (возвращает новую транспонированную матрицу)
     */
    public Matrix3 transpose() {
        Matrix3 result = new Matrix3(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.matrix[i][j] = this.matrix[j][i];
            }
        }
        return result;
    }

    /**
     * Вычисление определителя матрицы
     */
    public float determinant() {
        float a = matrix[0][0], b = matrix[0][1], c = matrix[0][2];
        float d = matrix[1][0], e = matrix[1][1], f = matrix[1][2];
        float g = matrix[2][0], h = matrix[2][1], i = matrix[2][2];
        
        return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
    }

    /**
     * Получить копию матрицы в виде двумерного массива
     */
    public float[][] toArray() {
        float[][] result = new float[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, 3);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix3:\n");
        for (int i = 0; i < 3; i++) {
            sb.append("[");
            for (int j = 0; j < 3; j++) {
                sb.append(String.format("%.3f", matrix[i][j]));
                if (j < 2) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
