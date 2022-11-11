package matrizen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        System.out.println(minFinder(3, 124,31, 351,315,315,1353));
        int[] arr = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
        int[][] arr2 = new int[][]{ {1,2,3,4,5,6,7,8,9,10},{1,2,3,4,5,6,7,8,9,10} };
        System.out.println(Arrays.toString(swap(arr, 2, 3)));
        System.out.println(span(arr2));
        System.out.println(Arrays.toString(lettercounter("aAaa")));
    }
    public static double minFinder(double first, double ... others){
        double min = first;
        for (double val : others)
            min = Math.min(min, val);
        return min;
    }
    public static int[] swap(int[] arr, int i, int j){
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
        return arr;
    }
    public static int[] findInts(String s){
        ArrayList<Integer> arr = new ArrayList<>();
        int begin = 0;
        int end = 0;
        for (int i = 0; i<s.length()-1; i++){
            if (' '==s.charAt(i+1)){
                end = i;
                i++;
                arr.add(Integer.valueOf(s.substring(begin,end)));
                begin = i+1;
            }
        }
        return null;
    }
    public static int[] lettercounter(String s){
        s=s.toLowerCase();
        int[] letterCount = new int[26];
        for (int i = 'a'; i<'z';i++){
            for (int j = 0; j < s.length(); j++){
                if (s.charAt(j)==i){
                    letterCount[i-97]++;
                }
            }
        }
        return letterCount;
    }
    public static int span(int[][] arr){
        int min = arr[0][0];
        int max = arr[0][0];
        for (int i = 0; i < arr.length; i++){
            for(int j = 0; j< arr[i].length; j++){
                if (arr[i][j]<min){
                    min=arr[i][j];
                }
                else if(arr[i][j]>max){
                    max=arr[i][j];
                }
            }
        }
        return max-min;
    }
}
