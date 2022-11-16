package matrizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/matrizen/Matrix.txt");
        File fileA = new File("src/matrizen/MatrixA.txt");
        File fileB = new File("src/matrizen/MatrixB.txt");

        int[][]arr = MatrixOperations.readMatrix(file);
        printMatrix(arr);
        printMatrix(MatrixOperations.transpose(arr));
        int[][] matA = MatrixOperations.readMatrix(fileA);
        int[][] matB = MatrixOperations.readMatrix(fileB);
        printMatrix(MatrixOperations.product(matA,matB));

    }
    public static void printMatrix(int[][] arr){
        String s = Arrays.deepToString(arr);
        int start = 0;
        for (int i = 0; i < s.length()-2; i++){
            if ((s).startsWith("],", i)) {
                System.out.println(s.substring(start, i + 2));
                start = i + 2;
            }
        }
        System.out.printf(s.substring(start)+"\n");
    }
}
