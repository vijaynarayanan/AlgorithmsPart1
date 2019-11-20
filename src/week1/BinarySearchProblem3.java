package week1;

/*Binary Search is not the correct answer for this problem*/
public class BinarySearchProblem3 {
    int[] id;
    public BinarySearchProblem3(int N) {
        for (int i = 0; i <N ; i++) {
            id[i] = i;
        }
    }

    public int find (int x, int low, int high){
        int mid = (low+high)/2;

        if(low > high)
            return -low;

        if(x == id[mid]){
            return mid;
        }else if(x > id[mid]){
            return find(x,low, mid);
        }else {
            return find(x,mid+1, high);
        }
    }

    public boolean remove(int x){
        int index = find(x, 0, id.length-1);
        if(index >= 0){
            System.arraycopy(id,index+1,id,index,id.length-index - 1);
            return true;
        }
        return false;
    }
}
