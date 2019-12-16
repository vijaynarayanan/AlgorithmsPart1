package week3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class InsertionSort {
    public static void sort(int[] a){
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j >0 ; j--) {
                if (less(a[j], a[j-1])){
                    swap(a,j,j-1);
                }else {
                    break;
                }
            }
        }
    }

    private static boolean less(int i, int j){
        return i < j;
    }

    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        int[] a = {10,3,5,2,1,3,4,8,9,6,7};
        sort(a);
        System.out.println(Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }
}
