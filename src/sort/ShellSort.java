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
//            start每个子数组的起始，分别对每个子数组进行处理
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

//    内部三层循环变为两层
    public static <E extends Comparable<E>> void sort2(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
//            从第一组的第二个元素依次遍历，所有元素一起处理
//            对data[h ...]进行插入排序
            for (int i = h; i < data.length; i++) {
                E temp = data[i];
                int j;
                for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }
            h /= 2;
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] data) {
        int h = 1;
//        使用可变序列, 1, 4, 13, 40 ...
        while (h < data.length) h = h * 3 + 1;
        while (h >= 1) {
//            从第一组的第二个元素依次遍历，所有元素一起处理
//            对data[h ...]进行插入排序
            for (int i = h; i < data.length; i++) {
                E temp = data[i];
                int j;
                for (j = i; j - h >= 0 && temp.compareTo(data[j - h]) < 0; j -= h) {
                    data[j] = data[j - h];
                }
                data[j] = temp;
            }
            h /= 3;
        }
    }

    public static void main(String[] args) {
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("ShellSort", arr);
        SortingHelper.sortTest("ShellSort2", arr2);
//        SortingHelper.sortTest("MergeSort4", arr2);
        SortingHelper.sortTest("ShellSort3", arr3);
//        SortingHelper.sortTest("InsertionSort2", arr3);
//
//        System.out.println("ordered...");
//        arr = ArrayGenerator.generateOrderedArray(n);
//        arr2 = Arrays.copyOf(arr, arr2.length);
//        SortingHelper.sortTest("SelectionSort", arr);
//        SortingHelper.sortTest("InsertionSort2", arr2);
    }
}
