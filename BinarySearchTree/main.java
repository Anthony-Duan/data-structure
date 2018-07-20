package BinarySearchTree;

/**
 * @ Description:
 * @ Date: Created in 10:00 20/07/2018
 * @ Author: Anthony_Duan
 */
public class main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 4, 2, 1, 6, 7, 3};
        for (int num :
                nums) {
            bst.add(num);
        }
        bst.preOrder();
        System.out.println();
        bst.inOrder();
        System.out.println();
        bst.postOrder();
        System.out.println();


        System.out.println(bst);
    }
}
