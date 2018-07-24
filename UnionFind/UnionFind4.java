package UnionFind;

/**
 * @ Description: 第四版并查集 优化指向问题
 * @ Date: Created in 13:53 22/07/2018
 * @ Author: Anthony_Duan
 */
public class UnionFind4 implements UF {


    /**
     * 如果根据集合中元素的个数来确定合并的方向是不够合理的
     * 准确的来说应该根据集合树的高度来决定合并的方向
     * 应该让高度低的集合并到高度高的集合
     */
    private int[] parent;

    //保存集合树的高度
    private int[] rank;

    public UnionFind4(int size) {
        rank = new int[size];
        parent = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }


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


        /**
         * 根据啷个元素所在树的rank不同判断合并的方向
         * 将rank低的集合合并到rank高的集合上
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else { //此时是高度相同的情况
            parent[pRoot] = qRoot;
            //此时需要维护 rank
            rank[qRoot] += 1;
        }


    }
}
