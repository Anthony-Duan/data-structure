package UnionFind;

/**
 * @ Description: 第六版并查集 路径压缩高度为2的树
 * @ Date: Created in 14:31 22/07/2018
 * @ Author: Anthony_Duan
 */
public class UnionFind6 implements UF {

    private int[] rank;
    private int[] parent;

    public UnionFind6(int size) {
        rank = new int[size];
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = i;
        }
    }

    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }

        //路径压缩优化为2  递归算法
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];

    }


    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }


        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
