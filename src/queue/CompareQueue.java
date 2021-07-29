package queue;

import java.util.Random;

public class CompareQueue {

    private static double testQueue(Queue<Integer> q, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double t1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time " + t1 + " s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double t2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time " + t2 + " s");

        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        double t3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time " + t3 + " s");
    }
}
