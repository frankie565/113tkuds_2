import java.util.Scanner;

public class MatrixCalculator {

    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length, cols = A[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length, colsA = A[0].length;
        int rowsB = B.length, colsB = B[0].length;
        if (colsA != rowsB) {
            throw new IllegalArgumentException("無法相乘：A的欄數不等於B的列數");
        }

        int[][] result = new int[rowsA][colsB];
        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][i] = matrix[i][j];
        return result;
    }

    public static void findMaxMin(int[][] matrix) {
        int max = matrix[0][0];
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }
        System.out.println("最大值: " + max);
        System.out.println("最小值: " + min);
    }

  
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%5d", val);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] B = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] C = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A:");
        printMatrix(A);

        System.out.println("\n矩陣 B:");
        printMatrix(B);

        System.out.println("\n矩陣 A + B:");
        printMatrix(add(A, B));

        System.out.println("\n矩陣 A × C:");
        printMatrix(multiply(A, C));

        System.out.println("\n矩陣 A 的轉置:");
        printMatrix(transpose(A));

        System.out.println("\n矩陣 A 的最大最小值:");
        findMaxMin(A);

        sc.close();
    }
}

