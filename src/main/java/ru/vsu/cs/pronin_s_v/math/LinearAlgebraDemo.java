package ru.vsu.cs.pronin_s_v.math;

/**
 * Демонстрационный класс для работы с библиотекой линейной алгебры
 * 
 * Этот класс показывает примеры использования всех классов библиотеки:
 * - Векторы (Vector2, Vector3, Vector4)
 * - Матрицы (Matrix3, Matrix4)
 * 
 * @author pronin_s_v
 * @version 1.0
 */
public class LinearAlgebraDemo {
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("   Демонстрация библиотеки линейной алгебры");
        System.out.println("═══════════════════════════════════════════════════════\n");
        
        demonstrateVectors();
        demonstrateMatrices();
        demonstrateAdvancedOperations();
        
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("   Демонстрация завершена успешно!");
        System.out.println("═══════════════════════════════════════════════════════");
    }
    
    /**
     * Демонстрация работы с векторами
     */
    private static void demonstrateVectors() {
        System.out.println("📐 ДЕМОНСТРАЦИЯ РАБОТЫ С ВЕКТОРАМИ\n");
        
        // Vector2 - двумерные векторы
        System.out.println("─── Vector2 (двумерные векторы) ───");
        Vector2 v2a = new Vector2(3.0f, 4.0f);
        Vector2 v2b = new Vector2(1.0f, 2.0f);
        
        System.out.println("Вектор a: " + v2a);
        System.out.println("Вектор b: " + v2b);
        System.out.println("Длина вектора a: " + v2a.length() + " (ожидается: 5.0)");
        System.out.println("Сложение: a + b = " + v2a.add(v2b));
        System.out.println("Вычитание: a - b = " + v2a.subtract(v2b));
        System.out.println("Умножение на 2: a * 2 = " + v2a.multiply(2.0f));
        System.out.println("Скалярное произведение: a · b = " + v2a.dot(v2b));
        System.out.println("Нормализованный вектор: " + v2a.normalize() + " (длина = " + v2a.normalize().length() + ")");
        
        // Vector3 - трехмерные векторы
        System.out.println("\n─── Vector3 (трехмерные векторы) ───");
        Vector3 v3a = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 v3b = new Vector3(0.0f, 1.0f, 0.0f);
        
        System.out.println("Вектор a: " + v3a);
        System.out.println("Вектор b: " + v3b);
        System.out.println("Векторное произведение: a × b = " + v3a.cross(v3b));
        System.out.println("  (результат перпендикулярен обоим векторам)");
        System.out.println("Скалярное произведение: a · b = " + v3a.dot(v3b) + " (перпендикулярные векторы)");
        
        // Vector4 - четырехмерные векторы
        System.out.println("\n─── Vector4 (четырехмерные векторы) ───");
        Vector4 v4a = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 v4b = new Vector4(5.0f, 6.0f, 7.0f, 8.0f);
        
        System.out.println("Вектор a: " + v4a);
        System.out.println("Вектор b: " + v4b);
        System.out.println("Сложение: a + b = " + v4a.add(v4b));
        System.out.println("Скалярное произведение: a · b = " + v4a.dot(v4b));
        
        // Создание Vector4 из Vector3 (для однородных координат)
        Vector3 v3 = new Vector3(10.0f, 20.0f, 30.0f);
        Vector4 v4from3 = new Vector4(v3, 1.0f);
        System.out.println("Vector4 из Vector3: " + v4from3);
        
        System.out.println();
    }
    
    /**
     * Демонстрация работы с матрицами
     */
    private static void demonstrateMatrices() {
        System.out.println("🔢 ДЕМОНСТРАЦИЯ РАБОТЫ С МАТРИЦАМИ\n");
        
        // Matrix3 - матрицы 3×3
        System.out.println("─── Matrix3 (матрицы 3×3) ───");
        
        // Создание единичной матрицы
        Matrix3 identity3 = new Matrix3();
        System.out.println("Единичная матрица:");
        printMatrix3(identity3);
        
        // Создание матрицы из массива
        float[][] data3 = {
            {1.0f, 2.0f, 3.0f},
            {4.0f, 5.0f, 6.0f},
            {7.0f, 8.0f, 9.0f}
        };
        Matrix3 m3 = new Matrix3(data3);
        System.out.println("Матрица из массива:");
        printMatrix3(m3);
        
        // Операции с матрицами
        Matrix3 m3a = new Matrix3();
        m3a.set(0, 0, 2.0f);
        m3a.set(1, 1, 2.0f);
        m3a.set(2, 2, 2.0f);
        
        Matrix3 m3b = new Matrix3();
        m3b.set(0, 0, 3.0f);
        m3b.set(1, 1, 3.0f);
        m3b.set(2, 2, 3.0f);
        
        System.out.println("Сложение матриц:");
        printMatrix3(m3a.add(m3b));
        
        System.out.println("Умножение матриц (единичная * любая = та же):");
        printMatrix3(identity3.multiply(m3));
        
        // Умножение матрицы на вектор
        Vector3 v3 = new Vector3(1.0f, 2.0f, 3.0f);
        Vector3 result3 = m3a.multiply(v3);
        System.out.println("Умножение матрицы на вектор (2,2,2) * (1,2,3) = " + result3);
        
        // Транспонирование
        System.out.println("Транспонированная матрица:");
        printMatrix3(m3.transpose());
        
        // Определитель
        System.out.println("Определитель матрицы: " + m3.determinant());
        
        // Matrix4 - матрицы 4×4
        System.out.println("\n─── Matrix4 (матрицы 4×4) ───");
        
        // Единичная матрица
        Matrix4 identity4 = new Matrix4();
        System.out.println("Единичная матрица 4×4:");
        printMatrix4(identity4);
        
        // Создание диагональной матрицы
        Matrix4 diagonal4 = new Matrix4(true);
        diagonal4.set(0, 0, 2.0f);
        diagonal4.set(1, 1, 3.0f);
        diagonal4.set(2, 2, 4.0f);
        diagonal4.set(3, 3, 5.0f);
        
        System.out.println("Диагональная матрица:");
        printMatrix4(diagonal4);
        
        // Умножение на вектор
        Vector4 v4 = new Vector4(1.0f, 2.0f, 3.0f, 4.0f);
        Vector4 result4 = diagonal4.multiply(v4);
        System.out.println("Умножение матрицы на вектор: " + result4);
        
        System.out.println();
    }
    
    /**
     * Демонстрация продвинутых операций
     */
    private static void demonstrateAdvancedOperations() {
        System.out.println("🚀 ДЕМОНСТРАЦИЯ ПРОДВИНУТЫХ ОПЕРАЦИЙ\n");
        
        // Обратная матрица
        System.out.println("─── Обратная матрица (Matrix4) ───");
        Matrix4 m4 = new Matrix4();
        m4.set(0, 0, 2.0f);
        m4.set(1, 1, 2.0f);
        m4.set(2, 2, 2.0f);
        m4.set(3, 3, 2.0f);
        
        System.out.println("Исходная матрица:");
        printMatrix4(m4);
        
        Matrix4 inverse = m4.inverse();
        System.out.println("Обратная матрица:");
        printMatrix4(inverse);
        
        Matrix4 product = m4.multiply(inverse);
        System.out.println("Проверка: A * A^(-1) = I (единичная матрица):");
        printMatrix4(product);
        
        // Определитель
        System.out.println("Определитель исходной матрицы: " + m4.determinant());
        
        // Векторное произведение для нахождения нормали
        System.out.println("\n─── Векторное произведение для нахождения нормали ───");
        Vector3 edge1 = new Vector3(1.0f, 0.0f, 0.0f);
        Vector3 edge2 = new Vector3(0.0f, 1.0f, 0.0f);
        Vector3 normal = edge1.cross(edge2).normalize();
        System.out.println("Ребро 1: " + edge1);
        System.out.println("Ребро 2: " + edge2);
        System.out.println("Нормаль (edge1 × edge2, нормализованная): " + normal);
        System.out.println("Длина нормали: " + normal.length() + " (должна быть 1.0)");
        
        // Использование векторов для вычисления угла
        System.out.println("\n─── Вычисление угла между векторами ───");
        Vector2 dir1 = new Vector2(1.0f, 0.0f);
        Vector2 dir2 = new Vector2(0.0f, 1.0f);
        float dot = dir1.dot(dir2);
        float angle = (float) Math.acos(dot / (dir1.length() * dir2.length()));
        System.out.println("Вектор 1: " + dir1);
        System.out.println("Вектор 2: " + dir2);
        System.out.println("Угол между векторами: " + Math.toDegrees(angle) + "° (ожидается 90°)");
        
        System.out.println();
    }
    
    /**
     * Вспомогательный метод для вывода матрицы 3×3
     */
    private static void printMatrix3(Matrix3 m) {
        for (int i = 0; i < 3; i++) {
            System.out.print("  [");
            for (int j = 0; j < 3; j++) {
                System.out.printf("%6.2f", m.get(i, j));
                if (j < 2) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println();
    }
    
    /**
     * Вспомогательный метод для вывода матрицы 4×4
     */
    private static void printMatrix4(Matrix4 m) {
        for (int i = 0; i < 4; i++) {
            System.out.print("  [");
            for (int j = 0; j < 4; j++) {
                System.out.printf("%6.2f", m.get(i, j));
                if (j < 3) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
