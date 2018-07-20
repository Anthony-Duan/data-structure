package Recursion.TestMyLinkedList;

/**
 * @ Description:
 * @ Date: Created in 15:55 17/07/2018
 * @ Author: Anthony_Duan
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 将一个数组转为链表的构造方法
     *
     * @param arr
     */
    public ListNode(int[] arr) {

        //这里先判断为空在判断长度为0是没有问题的写法 如果反过来写可能会报空指针异常
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();

    }
}
