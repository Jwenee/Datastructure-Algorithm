package stack;

import java.util.Random;

public class CompareStack {

    private static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();

        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }

        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        double t1 = testStack(arrayStack, opCount);
        System.out.println("ArrayStack, time " + t1 + " s");

        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        double t2 = testStack(linkedListStack, opCount);
        System.out.println("LinkedListStack, time " + t2 + " s");
    }
}
