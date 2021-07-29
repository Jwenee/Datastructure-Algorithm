package search;

import base.ArrayGenerator;

public class LinearSearch {

    private LinearSearch() {}

    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
//            if (data[i] == target) return i;
            if (data[i].equals(target)) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
//        Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
//
//        int res = LinearSearch.search(data, 16);
//        System.out.println(res);
//
//        int res2 = LinearSearch.search(data, 666);
//        System.out.println(res2);
//
//        Student[] students = {new Student("Alice"), new Student("Bob"), new Student("Tom")};
//
//        Student bob = new Student("Bob");
//        int res3 = LinearSearch.search(students, bob);
//        System.out.println(res3);
//        int n = 1000000;
        int[] sizes = {1000000, 10000000};
        for (int n : sizes) {
            Integer[] integers = ArrayGenerator.generateOrderedArray(n);
            long startTime = System.nanoTime();
            for (int i = 0; i < 100; i++) {
                LinearSearch.search(integers, n);
            }
            long endTime = System.nanoTime();

            double duration = (endTime - startTime) / 1000000000.0;
            System.out.println("n = " + n + " run 100 times: " + duration + " s");
        }

    }
}
