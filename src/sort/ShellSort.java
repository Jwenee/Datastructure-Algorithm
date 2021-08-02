package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;

public class ShellSort {

    private ShellSort() {}

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length / 2;
//        将数组不断划分为更小的子数组
        while (h >= 1) {
//            start每个子数组的起始
            for (int start = 0; start < h; start++) {
//            对data[start, start+h, start+2h, start+3h ...]进行插入排序
                for (int i = start + h; i < data.length; i += h) {
                    E temp = data[i];
                    int j;
                    for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                        data[j] = data[j - h];
                    }
                    data[j] = temp;
                }
            }
            h /= 2;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("MergeSort4", arr2);
//        SortingHelper.sortTest("InsertionSort2", arr3);
//
//        System.out.println("ordered...");
//        arr = ArrayGenerator.generateOrderedArray(n);
//        arr2 = Arrays.copyOf(arr, arr2.length);
//        SortingHelper.sortTest("SelectionSort", arr);
//        SortingHelper.sortTest("InsertionSort2", arr2);
    }
}
