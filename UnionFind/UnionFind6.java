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

    /**
     * 查找过程, 查找元素p所对应的集合编号
     * O(h)复杂度, h为树的高度
     *
     * @param p
     * @return
     */
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


    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q) {

        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        /**
         * 根据两个元素所在树的元素的个数不同判断合并的方向
         * 将元素个数少的集合合并到元素个数多的集合上
         */
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
