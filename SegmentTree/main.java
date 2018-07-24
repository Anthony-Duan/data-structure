package SegmentTree;

/**
 * @ Description:测试类
 * @ Date: Created in 20:06 21/07/2018
 * @ Author: Anthony_Duan
 */
public class main {
    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
        segTree.set(2, 5);
        System.out.println(segTree);
        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}
