package algorithm.application;

import algorithm.std.StdIn;

import java.util.Date;

public class WeightedQuickUnionUF {
    private int[] id;
    private int[] sz;
    private int count;
    public WeightedQuickUnionUF(int N) {
        count = N; id = new int[N]; sz = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            sz[i] = 1;
        }
    }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    public int count() {
        return count;
    }
// quick-find算法
//    public int find(int p) {
//        return id[p];
//    }
//    public void union(int p, int q) {
//        int pID = find(p), qID = find(q);
//        if (pID != qID) {
//            for (int i = 0; i < id.length; i++) if (id[i] == pID) id[i] = qID;
//            count--;
//        }
//    }

// quick-union算法
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }
//    public void union(int p, int q) {
//        int pRoot = find(p);
//        int qRoot = find(q);
//        if (pRoot != qRoot) {
//            id[pRoot] = id[qRoot];
//            count--;
//        }
//    }
    public void union(int p, int q) {
        int i = find(p); int j = find(q);
        if (i != j) {
            if (sz[i] < sz[j]) {
                id[i] = j;
                sz[j] += sz[i];
            } else {
                id[j] = i;
                sz[i] += sz[j];
            }
            count--;
        }
    }

    @Override
    public String toString() {
        Class self = WeightedQuickUnionUF.class;
        return self.getSimpleName();
    }

    public static void main(String[] args) {
        Date start = new Date();
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        Date end = new Date();
        double interval = (double)(end.getTime() - start.getTime()) / 1000;
        System.out.println(uf.count + " components");
        System.out.println("Algorithm " + uf + " takes " + interval + "s");
    }
}
