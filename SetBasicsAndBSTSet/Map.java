package SetBasicsAndBSTSet;

/**
 * @ Description: 映射接口
 * @ Date: Created in 21:54 20/07/2018
 * @ Author: Anthony_Duan
 */
public interface Map<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
