package sort;

import base.ArrayGenerator;
import base.SortingHelper;

import java.util.Arrays;

public class BubbleSort {

    private BubbleSort() {}

    public static <E extends Comparable<E>> void sort(E[] data) {
        for (int i = 0; i < data.length - 1; i++) {
//            arr[n-i, n)已经排好序
//            通过冒泡在arr[n-i-1]位置放上合适的元素
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0)
                    swap(data, j, j + 1);
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] data) {
        for (int i = 0; i < data.length - 1; i++) {
//            arr[n-i, n)已经排好序
//            通过冒泡在arr[n-i-1]位置放上合适的元素
            boolean isSwapped = false;
//            如果内层循环无交换，则已经有序
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    isSwapped = true;
                }
            }
            if (!isSwapped) break;
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] data) {
//        区别，此时i不再是固定+1
//        i代表第几轮循环，也代表末尾有i个元素有序
        for (int i = 0; i < data.length - 1;) {
//            arr[n-i, n)已经排好序
//            通过冒泡在arr[n-i-1]位置放上合适的元素
//            内部循环最后交换元素的索引
            int lastSwapIndex = 0;
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    lastSwapIndex = j + 1;
                }
            }
//            if (lastSwapIndex == 0) break;
//            该轮循环后，最后交换元素到数组末尾已经有序
            i = data.length - lastSwapIndex;
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        System.out.println("Random Array");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        System.out.println("Ordered Array");
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
    }
}
