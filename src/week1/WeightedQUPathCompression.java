package week1;

import java.util.Arrays;

public class WeightedQUPathCompression {

    int[] id;
    int[] size;

    public WeightedQUPathCompression(int N){
        id = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private boolean validate(int p) {
        if (p >= id.length || p < 0)
            throw new IllegalArgumentException(p + " is greater than " + id.length + " or less than 0");
        return true;
    }

    private int find(int p) {
        validate(p);
        while (id[p] != p) {
            id[p] = id[id[p]];  // This is the first variant to reduce the tree size. Connecting a node to great grandparent.
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)
            return;

        if (size[rootP] < size[rootQ]) {
            id[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            id[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
    }

    @Override
    public String toString() {
        return "WeightedQUPathCompression [id=" + Arrays.toString(id) + "]";
    }

    public static void main(String[] args) {
        WeightedQUPathCompression uf = new WeightedQUPathCompression(10);

        /* Quick Find algorithm */
        // Component 1
        uf.union(2, 1);
        uf.union(2, 7);

        // Component 2
        uf.union(6, 0);
        uf.union(6, 5);

        // Component 3
        uf.union(3, 4);
        uf.union(4, 8);
        uf.union(4, 9);
        System.out.println(uf.connected(1, 7));
        System.out.println(uf.connected(1, 8));

        System.out.println(uf.toString());

    }

}

