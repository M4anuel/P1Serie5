package matrizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MatrixOperations {
    public static int[][] product(int[][] matrix1, int[][] matrix2){
        int[][] matrix3 = new int[matrix1.length][matrix1.length];
        for(int i=0; i<matrix1.length;i++){
            //control
            if(matrix1.length!=matrix2[i].length){
                return null;
            }
            for(int j=0; j<=matrix1.length-1; j++){
                for(int k=0; k<=matrix1.length-1; k++){
                    matrix3[i][j] += matrix1[i][k]*matrix2[k][j];
                }
            }
        }
        return matrix3;
    }
    public static int[][] transpose(int[][] arr){
        for (int[] ints : arr) {
            if (arr.length != ints.length) {
                return null;
            }
        }

        int[][] array = new int[arr.length][arr[1].length];
        for (int i = 0;i < arr.length; i++){
            for (int j = 0; j < arr[1].length;j++){
                array[i][j]=arr[j][i];
            }
        }
        return array;
    }
    public static int[][] readMatrix(File file) throws FileNotFoundException {
        //file input stream
        Scanner scanner = new Scanner(file);
        int maxSize = 1;
        ArrayList<ArrayList<Integer>> arrlist = new ArrayList<>();
        while(scanner.hasNextLine()){
            ArrayList<Integer> arr = new ArrayList<>();
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            lineScanner.useDelimiter(" ");
            while (lineScanner.hasNext()) {
                arr.add(lineScanner.nextInt());
            }
            arrlist.add(arr);
            if ((arr.size() > maxSize)) {
                maxSize = arr.size();
            }
        }

        int[][] returnarr = new int[arrlist.size()][maxSize];
        for (int i = 0; i < arrlist.size(); i++){
            for (int j = 0; j < maxSize; j++){
                if (j<arrlist.get(i).size()){
                    returnarr[i][j] = arrlist.get(i).get(j);
                }
                else{returnarr[i][j] = 0;}
            }
        }
        return returnarr;
    }
}
