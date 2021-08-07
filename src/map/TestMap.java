package map;

import base.ArrayGenerator;

import java.util.Arrays;

public class TestMap {

    private static double test(Map<String, Integer> map, Integer[] arr) {
        long start = System.nanoTime();

        for (Integer integer : arr) {
            map.add(integer.toString(), integer);
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

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        AVLMap<String, Integer> avlMap = new AVLMap<>();

        System.out.println("BSTMap: " + test(bstMap, arr) + " s " + bstMap.getSize());
        System.out.println("LinkedListMap: " + test(linkedListMap, arr2) + " s " + linkedListMap.getSize());
        System.out.println("AVLMap: " + test(avlMap, arr3) + " s " + avlMap.getSize());
    }
}
