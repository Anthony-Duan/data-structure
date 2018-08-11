package SetBasicsAndBSTSet;

/**
 * @ Description: 基于二分搜索树的集合的实现
 * @ Date: Created in 20:25 20/07/2018
 * @ Author: Anthony_Duan
 */
public class BSTSet<E extends Comparable<E>> implements Set<E> {

    private BST<E> bst;

    public BSTSet() {
        bst = new BST<>();
    }


    //平均O(log(n))
    @Override
    public void add(E e) {
        bst.add(e);
    }

    //平均O(log(n))
    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    //平均O(log(n))
    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
