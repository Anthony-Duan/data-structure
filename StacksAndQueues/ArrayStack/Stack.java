package StacksAndQueues.ArrayStack;

/**
 * @ Description: Stack接口 标准的栈应该具有以下5个对外方法
 * @ Date: Created in 19:00 11/07/2018
 * @ Author: Anthony_Duan
 */
public interface Stack<E> {

    //得到栈中元素的个数
    int getSize();

    //判断栈是否为空
    boolean isEmpty();

    // 入栈操作
    void push(E e);

    //出栈操作
    E pop();

    //获取栈顶元素的值
    E peek();
}
