package sort;

import base.ArrayGenerator;
import base.SortingHelper;

public class SelectionSort {

    private SelectionSort() {}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
//        Integer[] arr = {1, 4, 2, 3, 6, 5};
//        SelectionSort.sort(arr);
//        for (int i : arr) {
//            System.out.println(i);
//        }

//        Student[] students = {new Student("Alice", 98), new Student("Bob", 100), new Student("Tom", 66)};
//        SelectionSort.sort(students);
//        for (Student student : students) {
//            System.out.println(student);
//        }

        int n = 10000;
        Integer[] integers = ArrayGenerator.generateRandomArray(n, n);
//        long startTime = System.nanoTime();
//        SelectionSort.sort(integers);
//        long endTime = System.nanoTime();
//        double duration = (endTime - startTime) / 1000000000.0;
//
//        if (!SortingHelper.isSorted(integers)) {
//            throw new RuntimeException("sort error");
//        }
//        System.out.println("n = " + n + " times: " + duration + " s");
        SortingHelper.sortTest("SelectionSort", integers);
        int n1 = 100000;
        Integer[] integers1 = ArrayGenerator.generateRandomArray(n1, n1);
        SortingHelper.sortTest("SelectionSort", integers1);
    }
}
