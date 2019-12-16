package week4;

import java.util.Arrays;

public class HeapSort {

    public static <T extends Comparable<T>> void sort(Comparable<T>[] arr){
        if(arr == null || arr.length == 0)
            return;
        T[] newArr = (T[]) new Comparable[arr.length + 1];
        System.arraycopy(arr,0, newArr,1, arr.length);

        int N = arr.length;
        for (int i = N/2; i >= 1; i--)
            sink(newArr, i, N);

        while (N > 1){
            swap(newArr, 1, N);
            sink(newArr,1, --N);
        }

        System.arraycopy(newArr,1,arr,0,arr.length);
    }

    public static <T extends Comparable<T>> void swim(Comparable<T>[] arr, int k){
        while(k > 1){
            int j = k/2;
            if(less(arr[j],arr[k]))
                swap(arr,k,j);
            k = j;
        }
    }

    public static <T extends Comparable<T>> void sink(Comparable<T>[] arr,int k, int N){
        while (2 * k <= N)
        {
            int j = 2 * k;
            if(j < N && less(arr[j], arr[j+1]))  j++;
            if(!less(arr[k], arr[j])) break;
            swap(arr, k, j); 
            k = j;
        }
    }

    private static <T extends Comparable<T>> boolean less(Comparable<T> a, Comparable<T> b){
        return a.compareTo((T)b) < 0;
    }

    private static <T extends Comparable<T>> void swap(Comparable<T>[] arr, int i, int j){
        Comparable<T> t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        //Integer[] arr = {37,36,50,29,44,20,46,55,59,11,18,30,41,1,49,33,48,34,47,24,4,54,51,15,12,14,21,40,7,39,16,22,45,56,42,26,6,58,5,2,52,23,25,53,35,3,10,28,27,19,9,8,38,17,43,57,13,31,32};
        Integer[] arr = {6,7,5,4,8,3,1,2};
        sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}
