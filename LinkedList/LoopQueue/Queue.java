package LinkedList.LoopQueue;

/**
 * @ Description:
 * @ Date: Created in 21:57 11/07/2018
 * @ Author: Anthony_Duan
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
