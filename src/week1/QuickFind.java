package week1;

import java.util.Arrays;

public class QuickFind {

    int id[];

    public QuickFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        validate(p);
        validate(q);

        return id[p] == id[q];
    }

    private boolean validate(int p) {
        if (p >= id.length || p < 0)
            throw new IllegalArgumentException(p + " is greater than " + id.length + " or less than 0");
        return true;
    }

    // Quick Find algorithm
    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pId = id[p];
        int qId = id[q];

        if (pId == qId)
            return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId)
                id[i] = pId;

        }
    }

    @Override
    public String toString() {
        return "UnionFind [id=" + Arrays.toString(id) + "]";
    }

    public static void main(String[] args) {
        QuickFind uf = new QuickFind(10);

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
        System.out.println(uf.connected(1, 7)); // true
        System.out.println(uf.connected(1, 8)); // false

        System.out.println(uf.toString());

    }
}
