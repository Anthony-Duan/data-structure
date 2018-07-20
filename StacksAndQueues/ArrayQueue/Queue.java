package StacksAndQueues.ArrayQueue;

/**
 * @ Description: Queue接口
 * @ Date: Created in 20:49 11/07/2018
 * @ Author: Anthony_Duan
 */
public interface Queue<E> {
    //获取队列的长度
    int getSize();

    //判断队列是否为空
    boolean isEmpty();

    //入队
    void enqueue(E e);

    //出队
    E dequeue();

    //得到队列尾部的元素的值
    E getFront();
}
