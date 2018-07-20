package Arrays.ArrayQueue;

/**
 * @ Description: ArrayQueue测试类
 * @ Date: Created in 21:01 11/07/2018
 * @ Author: Anthony_Duan
 */
public class Main {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            //  i % 3 == 2  即每添加3个数字就执行一次
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
