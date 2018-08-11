package StacksAndQueues.ArrayStack;

/**
 * @ Description: 使用Array动态数组类封装而成的栈实现，包括了动态增长与缩减
 * @ Date: Created in 19:02 11/07/2018
 * @ Author: Anthony_Duan
 */
public class ArrayStack<E> implements Stack<E> {


    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<E>(capacity);
    }

    public ArrayStack() {
        array = new Array<E>();
    }

    //O(1)
    @Override
    public int getSize() {
        return array.getSize();
    }

    //O(1)
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    //O(1)均摊复杂度
    @Override
    public void push(E e) {
        array.addLast(e);
    }

    //O(1)均摊复杂度
    @Override
    public E pop() {
        return array.removeLast();
    }

    //O(1)
    @Override
    public E peek() {
        return array.getLast();
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
