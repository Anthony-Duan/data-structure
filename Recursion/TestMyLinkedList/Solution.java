package Recursion.TestMyLinkedList;


/**
 * @ Description: 使用循环删除的方法解决 LeetCode 203号问题 链表相关
 * @ 问题地址： https://leetcode.com/problems/remove-linked-list-elements/description/
 * @ Date: Created in 15:24 17/07/2018
 * @ Author: Anthony_Duan
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {


        /**
         * 首先判断头结点是不是要删除的节点，
         * 要注意的是删除完第一个头结点后可能下一个头结点还是要删除的元素
         * 所以需要用循环判断删除
         */
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }


        /**
         * 如果链表的头结点为空，则返回空
         */
        if (head == null) {
            return head;
        }

        /**
         *删除的逻辑，先找到要删除元素的前一个节点
         */
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode = null;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements(head, 6);
        System.out.println(res);
    }

}
