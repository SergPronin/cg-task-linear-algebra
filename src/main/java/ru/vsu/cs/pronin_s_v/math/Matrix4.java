package ru.vsu.cs.pronin_s_v.math;

/**
 * Класс для работы с матрицами 4x4
 */
public class Matrix4 {
    private final float[][] matrix;

    /**
     * Создает единичную матрицу 4x4
     */
    public Matrix4() {
        matrix = new float[4][4];
        setIdentity();
    }

    /**
     * Создает нулевую матрицу 4x4
     */
    public Matrix4(boolean zero) {
        matrix = new float[4][4];
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
        if (m == null || m.length != 4) {
            throw new IllegalArgumentException("Matrix must be 4x4");
        }
        for (int i = 0; i < 4; i++) {
            if (m[i] == null || m[i].length != 4) {
                throw new IllegalArgumentException("Matrix must be 4x4");
            }
        }
        matrix = new float[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(m[i], 0, matrix[i], 0, 4);
        }
    }

    /**
     * Создает копию матрицы
     */
    public Matrix4(Matrix4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        matrix = new float[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(other.matrix[i], 0, matrix[i], 0, 4);
        }
    }

    /**
     * Устанавливает единичную матрицу
     */
    public void setIdentity() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = (i == j) ? 1.0f : 0.0f;
            }
        }
    }

    /**
     * Устанавливает нулевую матрицу
     */
    public void setZero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix[i][j] = 0.0f;
            }
        }
    }

    /**
     * Получить значение элемента матрицы
     */
    public float get(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return matrix[row][col];
    }

    /**
     * Установить значение элемента матрицы
     */
    public void set(int row, int col, float value) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        matrix[row][col] = value;
    }

    /**
     * Сложение матриц: this + other
     */
    public Matrix4 add(Matrix4 other) {
        if (other == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }
        Matrix4 result = new Matrix4(true);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.matrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.matrix[i][j] = this.matrix[i][j] - other.matrix[i][j];
            }
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sum = 0.0f;
                for (int k = 0; k < 4; k++) {
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
    public Vector4 multiply(Vector4 vector) {
        if (vector == null) {
            throw new IllegalArgumentException("Vector cannot be null");
        }
        float x = matrix[0][0] * vector.getX() + matrix[0][1] * vector.getY() 
                + matrix[0][2] * vector.getZ() + matrix[0][3] * vector.getW();
        float y = matrix[1][0] * vector.getX() + matrix[1][1] * vector.getY() 
                + matrix[1][2] * vector.getZ() + matrix[1][3] * vector.getW();
        float z = matrix[2][0] * vector.getX() + matrix[2][1] * vector.getY() 
                + matrix[2][2] * vector.getZ() + matrix[2][3] * vector.getW();
        float w = matrix[3][0] * vector.getX() + matrix[3][1] * vector.getY() 
                + matrix[3][2] * vector.getZ() + matrix[3][3] * vector.getW();
        return new Vector4(x, y, z, w);
    }

    /**
     * Транспонирование матрицы (возвращает новую транспонированную матрицу)
     */
    public Matrix4 transpose() {
        Matrix4 result = new Matrix4(true);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.matrix[i][j] = this.matrix[j][i];
            }
        }
        return result;
    }

    /**
     * Вычисление определителя матрицы (метод разложения по первой строке)
     */
    public float determinant() {
        float det = 0.0f;
        for (int j = 0; j < 4; j++) {
            float sign = (j % 2 == 0) ? 1.0f : -1.0f;
            det += sign * matrix[0][j] * minorDeterminant(0, j);
        }
        return det;
    }

    /**
     * Вычисление минора (определителя подматрицы 3x3)
     */
    private float minorDeterminant(int row, int col) {
        float[][] minor = new float[3][3];
        int mi = 0, mj = 0;
        for (int i = 0; i < 4; i++) {
            if (i == row) continue;
            mj = 0;
            for (int j = 0; j < 4; j++) {
                if (j == col) continue;
                minor[mi][mj] = matrix[i][j];
                mj++;
            }
            mi++;
        }
        // Вычисляем определитель 3x3
        float a = minor[0][0], b = minor[0][1], c = minor[0][2];
        float d = minor[1][0], e = minor[1][1], f = minor[1][2];
        float g = minor[2][0], h = minor[2][1], i = minor[2][2];
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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                float sign = ((i + j) % 2 == 0) ? 1.0f : -1.0f;
                adjugate.matrix[j][i] = sign * minorDeterminant(i, j) / det;
            }
        }
        return adjugate;
    }

    /**
     * Получить копию матрицы в виде двумерного массива
     */
    public float[][] toArray() {
        float[][] result = new float[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(matrix[i], 0, result[i], 0, 4);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matrix4:\n");
        for (int i = 0; i < 4; i++) {
            sb.append("[");
            for (int j = 0; j < 4; j++) {
                sb.append(String.format("%.3f", matrix[i][j]));
                if (j < 3) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
