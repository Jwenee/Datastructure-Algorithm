package tree;

import base.ArrayGenerator;

import java.util.Arrays;

public class TestTree {

    public static void main(String[] args) {
        int n = 100000;
        int bound = 10000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, bound);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        RBTree<Integer, Integer> rbTree = new RBTree<>();

        long start = System.nanoTime();
        for (Integer integer : arr) {
            if (avlTree.contains(integer))
                avlTree.set(integer, avlTree.get(integer) + 1);
            else
                avlTree.add(integer, 1);
        }
        long end = System.nanoTime();

        double time = (end - start) / 1000000000.0;

        System.out.println("AVLTree: " + time + " s");

        start = System.nanoTime();
        for (Integer integer : arr2) {
            if (rbTree.contains(integer))
                rbTree.set(integer, rbTree.get(integer) + 1);
            else
                rbTree.add(integer, 1);
        }
        end = System.nanoTime();

        time = (end - start) / 1000000000.0;

        System.out.println("RBTree: " + time + " s");
    }
}
