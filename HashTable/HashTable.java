package HashTable;

import java.util.TreeMap;

/**
 * @ Description: 哈希表的实现
 * @ Date: Created in 11:16 24/07/2018
 * @ Author: Anthony_Duan
 */
public class HashTable<K extends Comparable<K>, V> {


    /**
     * 哈希表的长度最好是素数并且原理2的幂，这样可以解决在模的结果中出现过多的哈希冲突
     * 下面是一个素数表 最后一个接近int值的最大上限
     */
    private final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    //最高冲突率
    private static final int upperTol = 10;
    //最低冲突率
    public static final int lowerTol = 2;
    private int capacityIndex = 0;


    private TreeMap<K, V>[] hashtable;
    private int size;
    //当前HashTable对象的长度
    private int M;

    public HashTable() {
        //初始化的长度是素数表中的0号索引对应的值
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            //每一个HashTable映射表中的元素都对应的为一个红黑树存放冲突值 链地址法
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {

        /**
         * 0x7FFFFFFF is 0111 1111 1111 1111 1111 1111 1111 1111
         * & 0x7fffffff 是为了让结果始终为正数 这样求模不会有错误
         */
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }


    public void add(K key, V value) {

        TreeMap<K, V> map = hashtable[hash(key)];
        //如果找到了就更新value
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {//如果没有找到就添加 并且维护size
            map.put(key, value);
            size++;

            /**
             * 如果冲突率过高 就扩容
             * 这里 size >= upperTol * M  这种写法是为了避免除法
             * 因为除法得出的结果为浮点型 在比较的时候整形会先转化为浮点型 这个操作其实是无用的
             */
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key :
                    map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashtable = newHashTable;
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            //哈希表的动态空间处理 如果冲突率低于下限
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
