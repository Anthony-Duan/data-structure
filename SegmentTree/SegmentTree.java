package SegmentTree;

/**
 * @ Description: 线段树的的数组实现
 * @ Date: Created in 19:52 21/07/2018
 * @ Author: Anthony_Duan
 */
public class SegmentTree<E> {
    private E[] tree;
    private E[] data;


    /**
     * 接受外部传入一个操作
     * 这个操作是是线段树区间的操作 根据业务而定 可以求区间的最大值 最小值 和 差等
     * 这里定义一个接口 将两个任意类型的数据融合为一个任意类型的数据
     * public interface Merger<E> {
     * E merger(E a, E b);
     * }
     */
    private Merger<E> merger;


    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        /**
         * 当元素为n个的时候   线段树需要的空间的最大上限为4n
         * （最坏情况是4n 如果n为2的倍数时为2n 不是2的倍数的时候线段树需要外加一层，
         * 满二叉树每层的节点个数是上一层的二倍,另外加一层也是加2n）
         */
        tree = (E[]) new Object[arr.length * 4];
        /**
         * 这里r传入的为arr.length-1 因为节点索引从0开始的
         */
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex 在哪个节点上构建线段树
     * @param l         线段树节点区间的左端点
     * @param r         线段树节点区间的右端点
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        /**
         * 递归的终止条件  也就是当区间只有一个元素的时候 也就是最底层的时候
         */
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        /**
         * 由于把线段树看成了完全二叉树，所以可以根据任意节点算出他的左孩子节点和有孩子节点
         * 当索引从0开始时
         * 左孩子节点索引 n*2+1
         * 右孩子节点索引 n*2+2
         */
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

//        int mid = (l + r) / 2; //这种写法  当l 与r 都非常大的时候可能超过最大上限而溢出
        int mid = l + (r - l) / 2;

        /**
         * 递归构建线段树
         * 当第一个递归执行完成 满足递归条件完成 tree[treeIndex] = data[l]; 赋值操作后
         * 第一个递归开始执行 此时一定满足递归条件 完成赋值
         * 最后 tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]); 会执行
         * 这三个操作可以看做是一轮操作
         */
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);
        tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中 左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中 右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 返回区间queryL - queryR 的值
     *
     * @param queryL
     * @param queryR
     * @return
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为跟的线段树中[l...r]的范围中 搜索区间为queryL - queryR 的值
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param queryL
     * @param queryR
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {


        /**
         * 递归终止条件
         * 当区间的范围刚好等于搜索区间的范围的时候 递归结束
         */
        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        /**
         * 我们知道每个区间的孩子节点的范围总是从父节点中间划分
         */
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);


        /**
         * 第一种情况 当要搜索的区间范围全部在该区间的右孩子节点的区间内时
         */
        if (queryL >= mid + 1) {
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        }
        /**
         * 第二种情况  当要搜索的区间范围全部在该节点的左孩子节点的区间内时
         */
        else if (queryR <= mid) {
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }

        /**
         * 如果不属于前两种情况 则进入第三种情况
         * 搜索区间的范围在左孩子和右孩子区间都存在
         * 这种情况下 分别搜索左 右孩子节点区间内的结果
         * 然后进行merger操作
         */
        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    /**
     * 将index 为值的值更新为e
     *
     * @param index 索引
     * @param e     值
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * 在以treeIndex为根的线段树中更新index 的值为e
     *
     * @param treeIndex
     * @param l
     * @param r
     * @param index
     * @param e
     */
    private void set(int treeIndex, int l, int r, int index, E e) {

        /**
         * 递归终止条件 如果区间缩小为1个元素  这这个元素就为index
         * 此时l==r&&l==index
         */
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);


        /**
         * 第一种情况
         * 如果index 在右孩子树中
         */
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else {
            /**
             * 第二种情况
             * 如果index 在左孩子树中
             */
            set(rightTreeIndex, l, mid, index, e);
        }
        //每次递归条件满足后都会执行该融合操作 所以这个操作会随着递归的回溯更新所有涉及的节点
        tree[treeIndex] = merger.merger(tree[leftTreeIndex], tree[rightTreeIndex]);

    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                res.append(tree[i]);
            } else {
                res.append("null");
            }
            if (i != tree.length - 1) {
                res.append(',');
            }
        }
        res.append(']');
        return res.toString();
    }
}
