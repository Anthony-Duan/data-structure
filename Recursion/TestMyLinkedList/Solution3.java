package TestMyLinkedList;


/**
 * @ Description: 使用虚拟头结点循环删除节点 LeetCode 203号问题 链表相关(简化版)
 * @ 问题地址： https://leetcode.com/problems/remove-linked-list-elements/description/
 * @ Date: Created in 15:43 17/07/2018
 * @ Author: Anthony_Duan
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        /**
         * 注意这里返回的一定是dummyHead.next 不能是head
         * 因为可能头结点就是要删除的节点 但是head已经不是头结点了
         */
        return dummyHead.next;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution2()).removeElements(head, 6);
        System.out.println(res);
    }
}
