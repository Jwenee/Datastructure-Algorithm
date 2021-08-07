package set;

import base.ArrayGenerator;

import java.util.Arrays;

public class TestSet {

    private static double test(Set<Integer> set, Integer[] arr) {
        long start = System.nanoTime();

        for (Integer integer : arr) {
            set.add(integer);
        }

        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 100000;
        int bound = 10000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, bound);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);

        BSTSet<Integer> bstSet = new BSTSet<>();
        LinkedListSet<Integer> linkedListSet = new LinkedListSet<>();
        AVLSet<Integer> avlSet = new AVLSet<>();

        System.out.println("BSTSet: " + test(bstSet, arr) + " s." + bstSet.getSize());
        System.out.println("linkedListSet: " + test(linkedListSet, arr2) + " s." + linkedListSet.getSize());
        System.out.println("AVLSet: " + test(avlSet, arr3) + " s." + avlSet.getSize());
    }
}
