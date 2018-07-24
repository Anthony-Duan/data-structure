package HeapAndPriorityQueue;

/**
 * @ Description: 利用动态数组实现最大堆结构
 * @ Date: Created in 11:46 21/07/2018
 * @ Author: Anthony_Duan
 */
public class MaxHeap<E extends Comparable<E>> {


    /**
     * 堆也可以说是一个完全二叉树，就是除了最底层的，其它层是满的
     * 所以可以根据任意一节点的索引计算出他的父节点和左右孩子节点的索引
     * 堆分为最大堆和最小堆
     * 最大堆： 除了根节点以外的任何节点都满足其父节点大于任意一孩子节点
     * 最小堆：除了根节点以外的任何节点都满足其父节点小于任意一孩子节点
     */
    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    //Heapify操作  将一个数组堆化  找到最后一个元素的父节点  然后从这个父节点往上遍历执行siftDown操作
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆中元素的个数
    public int size() {
        return data.getSize();
    }

    //返回一个布尔值 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回完全二叉树的数组表示中，一个索引所表示元素的父节点的元素的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    //返回二叉树的数组表示中一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    //返回完全二叉树的数组表示中一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * 在二叉堆的末尾添加元素 然后执行上浮操作
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮操作
     * 如果该节点的父节点小于该节点 就交换两个节点
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
        }
    }

    //看堆中最大的元素
    public E findMax() {
        //如果二叉堆为空则没有最大元素
        if (data.getSize() - 1 == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中最大的元素
     * 取出最大的元素后 将0位置与 二叉堆中最后一个索引元素交换位置
     * 此时最大值被换到了最后一个索引的位置 然后执行删除最后一个元素操作
     * 然后对0位置再次执行下沉操作
     *
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        //当有左孩子节点的时候
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);//在此轮循环中 data[k]和data[j]将交换位置

            //当有右孩子节点并且右孩子节点比左孩子节点大
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
//                j++;  //两种写法都可
                j = rightChild(k);
            }
            //data[j] 是leftChild和rigthChild中的最大的那值

            //如果k节点本身就大于他的左右孩子节点 就不作处理 跳出while循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            //否则就交换这两个节点 并且把j节点赋值给k节点 继续递归下沉操作
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中的最大元素，并且替换成元素e
     * 首先取出最大元素的 然后将元素e设置为0位置 对其执行下沉操作
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }
}
