package base;

import sort.*;

public class SortingHelper {

    private SortingHelper() {}

    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) return false;
        }
        return true;
    }

    public static <E extends Comparable<E>> void sortTest(String sortName, E[] arr) {
        long startTime = System.nanoTime();
        if ("SelectionSort".equals(sortName)) {
            SelectionSort.sort(arr);
        }
        if ("InsertionSort".equals(sortName)) {
            InsertionSort.sort(arr);
        }
        if ("InsertionSort2".equals(sortName)) {
            InsertionSort.sortTwo(arr);
        }
        if ("MergeSort".equals(sortName)) {
            MergeSort.sort(arr);
        }
        if ("MergeSort2".equals(sortName)) {
            MergeSort.sort2(arr);
        }
        if ("MergeSort4".equals(sortName)) {
            MergeSort.sort4(arr);
        }
        if ("MergeSortBU".equals(sortName)) {
            MergeSort2.sortBU(arr);
        }
        if ("QuickSort".equals(sortName)) {
            QuickSort.sort(arr);
        }
        if ("QuickSort2".equals(sortName)) {
            QuickSort2.sort(arr);
        }
        if ("HeapSort".equals(sortName)) {
            HeapSort.sort(arr);
        }
        if ("HeapSort2".equals(sortName)) {
            HeapSort.sort2(arr);
        }
        if ("BubbleSort".equals(sortName)) {
            BubbleSort.sort(arr);
        }
        if ("BubbleSort2".equals(sortName)) {
            BubbleSort.sort2(arr);
        }
        if ("BubbleSort3".equals(sortName)) {
            BubbleSort.sort3(arr);
        }
        if ("ShellSort".equals(sortName)) {
            ShellSort.sort(arr);
        }
        if ("ShellSort2".equals(sortName)) {
            ShellSort.sort2(arr);
        }
        if ("ShellSort3".equals(sortName)) {
            ShellSort.sort3(arr);
        }
        long endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000000.0;

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(sortName + "sort error");
        }
        System.out.println(String.format("%s , n = %d : %f s", sortName, arr.length, duration));
    }
}
