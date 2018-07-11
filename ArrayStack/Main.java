package ArrayStack;

/**
 * @ Description: ArrayStack 测试类
 * @ Date: Created in 19:13 11/07/2018
 * @ Author: Anthony_Duan
 */
public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<Integer>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}
