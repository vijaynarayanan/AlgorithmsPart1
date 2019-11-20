package week1;

import java.util.Arrays;

/*
Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns the
largest element in the connected component containing i. The operations,union(),connected(), and find() should all take
logarithmic time or better.

For example, if one of the connected components is
{1,2,6,9}, then the find() method should return 9 for each of the four elements in the connected components
*/
public class WeightedQUPathCompressionProblem1 {
    int[] id;

    public WeightedQUPathCompressionProblem1(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    private boolean validate(int p) {
        if (p >= id.length || p < 0)
            throw new IllegalArgumentException(p + " is greater than " + id.length + " or less than 0");
        return true;
    }
    public int find(int p){
        validate(p);
        while(id[p] != p){
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
    public void union(int p, int q){
        validate(p);
        validate(q);
        int rootP = find(p);
        int rootQ = find(q);

        if(rootP > rootQ){
            id[rootQ] = rootP;
        }else{
            id[rootP] = rootQ;
        }
    }
    public boolean connected(int p, int q){
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    @Override
    public String toString() {
        return "WeightedQUPathCompressionProblem1{" +
                "id=" + Arrays.toString(id) +
                '}';
    }

    public static void main(String[] args) {
        WeightedQUPathCompressionProblem1 wq = new WeightedQUPathCompressionProblem1(10);
        wq.union(0,1);
        wq.union(0,2);
        wq.union(0,3);
        wq.union(0,4);
        wq.union(0,5);
        System.out.println(wq.find(4));

        wq.union(0,6);
        wq.union(0,7);
        wq.union(0,7);
        wq.union(0,8);
        System.out.println(wq.find(4));
        wq.union(0,9);

        System.out.println(wq.find(4));
        System.out.println(wq);
        System.out.println(wq.connected(3,4));
        System.out.println(wq);
        System.out.println(wq.connected(8,4));
        System.out.println(wq);
        System.out.println(wq.connected(8,2));
        System.out.println(wq);
    }
}
