package uniyar.dorovskih;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class Matrix {
    private int rows;
    private int cols;
    private double[][] data;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double getElement(int i, int j) {
        return data[i][j];
    }

    public void setElement(int i, int j, double value) {
        data[i][j] = value;
    }

    public static Matrix readFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String[] dimensions = br.readLine().split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);

            Matrix matrix = new Matrix(rows, cols);

            for (int i = 0; i < rows; i++) {
                String[] rowValues = br.readLine().split(" ");
                for (int j = 0; j < cols; j++) {
                    matrix.setElement(i, j, Double.parseDouble(rowValues[j]));
                }
            }

            return matrix;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[] readBasis() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номера столбцов(начинаются с нуля) базисных переменных через пробел:");

        String[] input = scanner.nextLine().split(" ");
        int[] basisVariables = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            basisVariables[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(basisVariables);

        return basisVariables;
    }

    public void forwardGauss(int[] basisVariables) {
        for (int i = 0; i < rows - 1; i++) {
            for (int j = i + 1; j < rows; j++) {
                double factor = data[j][basisVariables[i]] / data[i][basisVariables[i]];

                for (int k = i; k < cols; k++) {
                    data[j][k] -= factor * data[i][k];
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            double diagonalElement = data[i][basisVariables[i]];
            for (int j = basisVariables[i]; j < cols; j++) {
                data[i][j] /= diagonalElement;
            }
        }
    }

    public void backwardGauss(int[] basisVariables) {
        for (int i = rows - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                double factor = data[j][basisVariables[i]] / data[i][basisVariables[i]];

                for (int k = cols - 1; k >= 0; k--) {
                    data[j][k] -= factor * data[i][k];
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            double diagonalElement = data[i][basisVariables[i]];
            for (int j = basisVariables[i]; j < cols; j++) {
                data[i][j] /= diagonalElement;
            }
        }
    }

}