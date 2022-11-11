package matrizen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MatrixOperations {
    public static int[][] readMatrix(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int lineNumber = 1;
        int maxSize = 1;
        ArrayList<ArrayList<Integer>> arrlist = new ArrayList<>();
        while(scanner.hasNextLine()){
            ArrayList<Integer> arr = new ArrayList<>();
            String line = scanner.nextLine();
            int end = 0;
            for (int i=0;i<line.length()-1;i++){
                if (line.charAt(i+1)==' '){
                    end=i+1;
                    arr.add(Integer.valueOf(line.substring(i, end)));
                }
            }
            arr.add(Integer.valueOf(line.substring(end+1)));
            arrlist.add(arr);
            if ((arr.size() > maxSize)) {
                maxSize = arr.size();
            }
            lineNumber++;
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
