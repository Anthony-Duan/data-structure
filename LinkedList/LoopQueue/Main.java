package LinkedList.LoopQueue;

import java.util.Random;

/**
 * @ Description: 测试循环队列与队列的出队的时间差异
 * @ Date: Created in 22:21 11/07/2018
 * @ Author: Anthony_Duan
 */
public class Main {

    private static double testQueue(Queue<Integer> q, int opCount) {
        Long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }
        Long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        //定义多少个操作数
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<Integer>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("StacksAndQueues.ArrayQueue, time: " + time1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<Integer>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LinkedList.LoopQueue, time: " + time2 + " s");


    }
}
