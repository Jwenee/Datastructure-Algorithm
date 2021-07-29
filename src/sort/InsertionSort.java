package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;

public class InsertionSort {

    private InsertionSort() {}

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
//            for (int j = i; j - 1 >= 0; j--) {
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1);
//                } else {
//                    break;
//                }
//            }
//            存在多次交换
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j, j - 1);
            }
        }
    }

    public static <E extends Comparable<E>> void sortTwo(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            E temp = arr[i];
            int j;
//            平移操作
            for (j = i; j - 1 >= 0 && temp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] arr, int l, int r) {
        for (int i = l; i <= r; i++) {
            E temp = arr[i];
            int j;
//            平移操作
            for (j = i; j - 1 >= l && temp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] dataSize = {10000, 100000};
        for (int i : dataSize) {
            System.out.println("random...");
            Integer[] integers = ArrayGenerator.generateRandomArray(i, i);
            Integer[] integers2 = Arrays.copyOf(integers, integers.length);
            SortingHelper.sortTest("SelectionSort", integers);
            SortingHelper.sortTest("InsertionSort2", integers2);

            System.out.println("ordered...");
            integers = ArrayGenerator.generateOrderedArray(i);
            integers2 = Arrays.copyOf(integers, integers.length);
            SortingHelper.sortTest("SelectionSort", integers);
            SortingHelper.sortTest("InsertionSort2", integers2);

        }
    }
}
