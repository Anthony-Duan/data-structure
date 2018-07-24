package UnionFind;

/**
 * @ Description: 第三版并查集的实现 优化节点指向，避免树过高的出现
 * @ Date: Created in 13:40 22/07/2018
 * @ Author: Anthony_Duan
 */
public class UnionFind3 implements UF {


    private int[] parent;


    //sz[i]表示以i为根的集合中元素的个数
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            //初始时，每个集合中的节点个数为1
            sz[i] = 1;
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
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;

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
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];

        } else { // sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
