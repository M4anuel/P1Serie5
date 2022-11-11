package matrizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class MatrixTest {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("src/matrizen/Matrix.txt");
        System.out.println(Arrays.deepToString(MatrixOperations.readMatrix(file)));
    }
}
