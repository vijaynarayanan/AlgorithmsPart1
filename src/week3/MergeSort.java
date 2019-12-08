package week3;

public class MergeSort {

    private void merge(Comparable[] arr,Comparable[] aux, int lo, int mid, int hi){

        if (hi + 1 - lo >= 0) System.arraycopy(arr, lo, aux, lo, hi + 1 - lo);

        int i = lo;
        int j = mid+1;
        for (int k = lo; k <=hi;k++){
            if(i > mid){
                arr[k] = aux[j++];
            }else if(j > hi){
                arr[k] = aux[i++];
            }else if(less(aux[j], aux[i])){
                arr[k] = aux[j++];
            }else{
                arr[k] = aux[i++];
            }
        }
    }

    private void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(lo >= hi) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a,aux,lo, mid, hi);
    }

    public void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length -1);
    }

    public static boolean less(Comparable i, Comparable j){
        return i.compareTo(j) < 0;
    }
}
