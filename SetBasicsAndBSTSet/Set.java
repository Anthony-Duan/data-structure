package SetBasicsAndBSTSet;

/**
 * @ Description: 集合接口
 * @ Date: Created in 20:24 20/07/2018
 * @ Author: Anthony_Duan
 */
public interface Set<E> {
    void add(E e);

    boolean contains(E e);

    void remove(E e);

    int getSize();

    boolean isEmpty();
}
