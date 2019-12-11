package week3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;

public class QuickSort {

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;

        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j+1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi){
        int i = lo;
        int j = hi + 1;
        while(true) {
            while (less(a[++i] ,a[lo])) if (i == hi) break;

            while (less(a[lo], a[--j] )) if(j == lo) break;

            if(i >= j) break;
            swap(a, i , j);
        }

        swap(a, lo, j);
        return j;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static void main(String[] args) {
        Integer[] ints = {9,2,4,5,6,1,7,8,3};
        sort(ints);

        Arrays.stream(ints).forEach(System.out::println);
    }

}
