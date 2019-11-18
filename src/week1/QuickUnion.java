package week1;

import java.util.Arrays;

public class QuickUnion {
    int id[];

    public QuickUnion(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);

        return find(p) == find(q);
    }

    public int find(int p) {
        validate(p);
        while (id[p] != p) {
            p = id[p];
        }
        return p;
    }

    // Quick Union algorithm
    public void union(int p, int q) {
        validate(p);
        validate(q);

        int rootP = find(p);
        int rootQ = find(q);

        if (rootP != rootQ) {
            id[rootP] = rootQ;
        }
    }

    private boolean validate(int p) {
        if (p >= id.length || p < 0)
            throw new IllegalArgumentException(p + " is greater than " + id.length + " or less than 0");
        return true;
    }

    @Override
    public String toString() {
        return "QuickUnion [id=" + Arrays.toString(id) + "]";
    }

    public static void main(String[] args) {
        QuickUnion uf = new QuickUnion(10);

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
