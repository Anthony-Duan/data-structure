package HeapAndPriorityQueue;

/**
 * @ Description: 队列接口
 * @ Date: Created in 12:44 21/07/2018
 * @ Author: Anthony_Duan
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
