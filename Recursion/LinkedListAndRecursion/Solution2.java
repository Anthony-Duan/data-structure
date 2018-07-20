package Recursion.LinkedListAndRecursion;

/**
 * @ Description: 使用循环删除的方法解决 LeetCode 203号问题 链表相关(简化版)
 * @ 问题地址： https://leetcode.com/problems/remove-linked-list-elements/description/
 * @ Date: Created in 15:37 17/07/2018
 * @ Author: Anthony_Duan
 */
public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {
        /**
         * 由于LeetCode提交只涉及逻辑不需要看考虑内存的释放
         * 所以可以省略一些内存释放的步骤
         */
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

}
