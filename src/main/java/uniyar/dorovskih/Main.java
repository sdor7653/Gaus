package uniyar.dorovskih;


import uniyar.dorovskih.Matrix;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try{

            // чтобы проверить основной метод решения нужно редактировать
            // файл: file.txt сначала количество строк потом количество столбцов
            // читает из файла 1 матрицу

            Matrix matrix = Matrix.readFile("file.txt");
            matrix.printMatrix();
            int[] basic = Matrix.readBasis();

            System.out.println("----------");

            matrix.forwardGauss(basic);
            matrix.printMatrix();
            matrix.backwardGauss(basic);
            System.out.println("Результат:");
            matrix.printMatrix();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}