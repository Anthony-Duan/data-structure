package SegmentTree;

/**
 * @ Description: 线段树区间操作的接口
 * @ Date: Created in 19:52 21/07/2018
 * @ Author: Anthony_Duan
 */
public interface Merger<E> {
    E merger(E a, E b);
}
