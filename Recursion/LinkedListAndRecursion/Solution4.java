package Recursion.LinkedListAndRecursion;

/**
 * @ Description: 使用递归解决删除链表中的某个元素  LeetCode 203号问题
 * @ Date: Created in 16:32 17/07/2018
 * @ Author: Anthony_Duan
 */
public class Solution4 {
    public ListNode removeElements(ListNode head, int val) {
        //递归的终止条件 最终的基本问题
        if (head == null) {
            return head;
        }

        /**
         * 调用递归
         * 每次传入的都是除了头结点的其他节点 缩小问题的范围
         * 返回的res 代表的上一个递归的结果
         */
        ListNode res = removeElements(head.next, val);

        //如果当前节点是要删除的节点 就返回当前递归中保存的上一个递归的结果 也就是忽略了当前头节点
        if (head.val == val) {
            return res;
        } else {
            //如果不是就把当前节点挂到上一个递归结果的前面作为新的头结点并返回
            head.next = res;
            return head;
        }
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution4()).removeElements(head, 6);
        System.out.println(res);
    }
}
