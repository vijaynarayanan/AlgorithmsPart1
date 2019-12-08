package week3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ShellSort {
    public static void sort(int[] a){
        int N = a.length;
        int h = 1;
        while (h < N / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, ...
        while (h >= 1) {  // h-sort the array.
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    swap(a, j, j - h);
            }
            h = h / 3;
        }
    }
    private static boolean less(int i,int j){
        return i < j;
    }

    private static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void main(String[] args) {
        int[] a = {37,36,50,29,44,20,46,55,59,11,18,30,41,1,49,33,48,34,47,24,4,54,51,15,12,14,21,40,7,39,16,22,45,56,42,26,6,58,5,2,52,23,25,53,35,3,10,28,27,19,9,8,38,17,43,57,13,31,32};
        sort(a);
        System.out.println(Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(",")));
    }
}
